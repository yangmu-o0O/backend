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
import java.time.LocalDateTime;
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

    //每30分钟执行一次 cron表达式
    @Scheduled(cron = "0 */30 * * * ?")
    private void getAccessToken(){
        log.info("定时任务刷新accessToken开始 : {}", LocalDateTime.now());
        Map<String,String> map = new HashMap<>();
        map.put("grant_type",WeixinUrl.GRANT_TYPE);
        map.put("appid",WxConfig.appId);
        map.put("secret",WxConfig.appSecret);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(WeixinUrl.GET_TOKEN+"?grant_type={grant_type}&appid={appid}&secret={secret}",String.class,map);
        log.info("定时任务刷新accessToken结束 : {} 新的token为:{}", LocalDateTime.now(),responseEntity.getBody());
    }

    /**
     * 这里没有配置RestTemplate,就在这里手动加载一下,不然任务执行找不到暂时没太苛刻的需求,后续可能需要配置下
     * @return RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
