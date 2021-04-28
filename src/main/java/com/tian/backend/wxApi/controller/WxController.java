package com.tian.backend.wxApi.controller;

import com.alibaba.fastjson.JSON;
import com.tian.backend.wxApi.model.ReplyTextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author muyang.tian
 * @date 2021/4/26 10:25
 */
@Slf4j
@RequestMapping("/wx")
@RestController
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class WxController {

    /**
     * 测试微信订阅号接口
     * @param signature 确认需要参数
     * @param timestamp 确认需要参数
     * @param nonce 确认需要参数
     * @param echostr 确认需要参数
     * @return echostr
     */
    @GetMapping
    public String test(@RequestParam(required = false) String signature,
                       @RequestParam(required = false) String timestamp,
                       @RequestParam(required = false) String nonce,
                       @RequestParam(required = false) String echostr){
        log.info("开始进行微信接口验证");
        List<String> list = new ArrayList<>();
        String token = "goodnight";
        list.add(timestamp);
        list.add(nonce);
        list.add(token);
        log.info("接收到的参数分别是:{}",list.toString());
        Collections.sort(list);
        //加密后的参数和signature进行比较,一样的话返回echostr
        if(DigestUtils.sha1Hex(list.get(0)+list.get(1)+list.get(2)).equals(signature)){
            log.info("微信接口验证成功");
            return echostr;
        }
        log.info("微信接口验证失败");
        return null;

    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ReplyTextMessage message(@RequestBody ReplyTextMessage input){
        log.info("接收为: {}", JSON.toJSON(input));
        log.info("接受到来自微信用户: {} 的消息: {}",input.getFromUserName(),input.getContent());
        ReplyTextMessage output = new ReplyTextMessage();
        output.setFromUserName(input.getToUserName());
        output.setToUserName(input.getFromUserName());
        output.setContent("你是真的帅");
        output.setCreateTime(new Date().getTime() / 1000);
        output.setMsgType("text");
        return output;
    }

}
