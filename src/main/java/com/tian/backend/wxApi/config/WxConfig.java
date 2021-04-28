package com.tian.backend.wxApi.config;

import org.springframework.stereotype.Component;

/**
 * @author muyang.tian
 * @date 2021/4/27 15:23
 */
@Component
public interface WxConfig {

    /**
     * 开发者ID(AppID)
     */
    String appId = "wx5bebdd774f6f4115";

    /**
     * 开发者密码(AppSecret)
     */
    String appSecret = "4f6f26e478e5cb30039c0a90921a4f98";
}
