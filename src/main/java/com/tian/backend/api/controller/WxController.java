package com.tian.backend.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.tian.backend.api.config.ApiConfig;
import com.tian.backend.api.config.ApiUrl;
import com.tian.backend.api.model.ReplyTextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author muyang.tian
 * @date 2021/4/26 10:25
 */
@Slf4j
@RequestMapping("/wx")
@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class WxController {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 测试微信订阅号接口
     *
     * @param signature 确认需要参数
     * @param timestamp 确认需要参数
     * @param nonce     确认需要参数
     * @param echostr   确认需要参数
     * @return echostr
     */
    @GetMapping
    public String test(@RequestParam(required = false) String signature,
                       @RequestParam(required = false) String timestamp,
                       @RequestParam(required = false) String nonce,
                       @RequestParam(required = false) String echostr) {
        log.info("开始进行微信接口验证");
        List<String> list = new ArrayList<>();
        String token = "goodnight";
        list.add(timestamp);
        list.add(nonce);
        list.add(token);
        log.info("接收到的参数分别是:{}", list.toString());
        Collections.sort(list);
        //加密后的参数和signature进行比较,一样的话返回echostr
        if (DigestUtils.sha1Hex(list.get(0) + list.get(1) + list.get(2)).equals(signature)) {
            log.info("微信接口验证成功");
            return echostr;
        }
        log.info("微信接口验证失败");
        return null;

    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ReplyTextMessage message(@RequestBody ReplyTextMessage input) {
        log.info("接受到来自微信用户: {} 的消息: {}", input.getFromUserName(), input.getContent());
        ReplyTextMessage output = new ReplyTextMessage();
        output.setFromUserName(input.getToUserName());
        output.setToUserName(input.getFromUserName());
        output.setContent(replyContent(input.getContent()));
        output.setCreateTime(System.currentTimeMillis() / 1000);
        output.setMsgType("text");
        return output;
    }

    private String replyContent(String msg) {
        int l = 2;
        String t = "天气";
        if(msg.length()> l && t.equals(msg.substring(msg.length()-2))){
            String address = msg.substring(0,msg.length()-2);
            Map<String,String> map = new HashMap<>(12);
            log.info("传入的地址为: {}",address);
            map.put("address",address);
            map.put("ak", ApiConfig.BaiduAk);
            ResponseEntity<String> addressEntity = restTemplate.getForEntity(ApiUrl.ADDRESS_URL, String.class,map);
            JSONObject addressJson = JSONObject.parseObject(addressEntity.getBody());
            String code = "status";
            if (addressJson.getInteger(code) != 0){
                return "您输入的地址:["+address+"]我看不太懂呢,麻烦换下/:<L>";
            }
            JSONObject location = addressJson.getJSONObject("result").getJSONObject("location");
            log.info("转化后的经纬度为: lat:{} lng:{}",location.get("lat"),location.get("lng"));
            map.put("key",ApiConfig.HeFengKey);
            map.put("location",location.getString("lng")+","+location.getFloat("lat"));
            ResponseEntity<JSONObject> weatherEntity = restTemplate.getForEntity(ApiUrl.THREE_DAY_WEATHER_URL, JSONObject.class,map);
            ResponseEntity<JSONObject> nowWeatherEntity = restTemplate.getForEntity(ApiUrl.NOW_WEATHER_URL, JSONObject.class,map);
            List<JSONObject> threeDayWeather = Objects.requireNonNull(weatherEntity.getBody()).getJSONArray("daily").toJavaList(JSONObject.class);
            JSONObject nowWeather = Objects.requireNonNull(nowWeatherEntity.getBody()).getJSONObject("now");
            JSONObject todayWeather = threeDayWeather.get(0);
            //暂时不需要后两天的
            StringBuilder content = new StringBuilder();
            if (todayWeather.getString("textDay").equals("晴")){
                content.append("今天天气很晴朗🌞 冲鸭!\n");
            }else if(todayWeather.getString("textDay").contains("雨")){
                content.append(address).append("今天可能会下雨🌧哦,宝贝\n");
            }else if (todayWeather.getString("textDay").contains("雷")){
                content.append(address).append("今天可能会打雷🌩呢,baby\n");
            }else {
                content.append(address).append("今日天气: ").append(todayWeather.getString("textDay")).append(" 💖\n");
            }
            content.append("今天温度在").append(todayWeather.getString("tempMax")).append("°到").append(todayWeather.getString("tempMin")).append("°左右\n")
                .append("当前温度在").append(nowWeather.getString("temp")).append("°\n")
                .append("当前体感温度在").append(nowWeather.getString("feelsLike")).append("°左右\n")
                .append("今天风向是").append(todayWeather.getString("windDirDay")).append("哦\n")
                .append("今天的风在:").append(todayWeather.getString("windScaleDay")).append("级\n")
                .append("☀️出现在").append(todayWeather.getString("sunrise")).append(",在").append(todayWeather.getString("sunset")).append("落下\n")
                .append("🌙️出现在").append(todayWeather.getString("moonrise")).append(",在").append(todayWeather.getString("moonset")).append("藏起来\n");
                Integer uvIndex = todayWeather.getInteger("uvIndex");
                if (uvIndex >= 10){
                    content.append("今天的紫外线好强的,足足有").append(uvIndex).append("级,出门一定要防晒鸭!!!\n");
                }else if (uvIndex >= 5){
                    content.append("今天的紫外线有").append(uvIndex).append("级,出门要注意防晒鸭!!!\n");
                }else{
                    content.append("今天的紫外线强度在").append(uvIndex).append("级\n");
                }
                content.append("今天的天上有").append(todayWeather.getInteger("cloud")).append("%的☁️");
            return content.toString();
        }
        ResponseEntity<String> loveTalkEntity;
        String content;
        try {
            loveTalkEntity = restTemplate.getForEntity(ApiUrl.LOVE_TALK, String.class);
            content = loveTalkEntity.getBody();
        } catch (RestClientException e) {
            log.error("timeout了");
            loveTalkEntity = restTemplate.getForEntity(ApiUrl.LOVE_TALK, String.class);
            content = loveTalkEntity.getBody();
        }
        if (content == null){
            content = "我宁愿留在你方圆几里~~~";
        }
        return Objects.requireNonNull(content);
}

}
