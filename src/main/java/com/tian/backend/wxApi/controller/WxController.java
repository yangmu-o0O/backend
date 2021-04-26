package com.tian.backend.wxApi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.ArrayList;
import java.util.Collections;
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

    @GetMapping
    public String test(@RequestParam String signature,
                       @RequestParam String timestamp,
                       @RequestParam String nonce,
                       @RequestParam String echostr){
        List<String> list = new ArrayList<>();
        String token = "woxiaole";
        list.add(timestamp);
        list.add(nonce);
        list.add(token);
        Collections.sort(list);
        if(DigestUtils.sha1Hex(list.get(0)+list.get(1)+list.get(2)).equals(signature)){
            return echostr;
        }
        return null;

    }

}