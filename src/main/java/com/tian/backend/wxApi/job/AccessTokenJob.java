package com.tian.backend.wxApi.job;

import com.tian.backend.wxApi.config.WeixinUrl;
import com.tian.backend.wxApi.config.WxConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author muyang.tian
 * @date 2021/4/27 15:41
 */
@Slf4j
@Component
public class AccessTokenJob {


    @Resource
    private RestTemplate restTemplate;

    @Scheduled(cron = "0 */30 * * * ?")
    private void getAccessToken(){
        log.info("定时任务刷新accessToken开始");
        Map<String,String> map = new HashMap<>();
        map.put("grant_type",WeixinUrl.GRANT_TYPE);
        map.put("appid",WxConfig.appId);
        map.put("secret",WxConfig.appSecret);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(WeixinUrl.GET_TOKEN+"?grant_type={grant_type}&appid={appid}&secret={secret}",String.class,map);
        log.info("定时任务刷新accessToken结束 新的token为:{}",responseEntity.getBody());
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
