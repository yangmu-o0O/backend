package com.tian.backend.wxApi.config;

/**
 * 微信接口url类
 *
 * @author muyang.tian
 * @date 2021/4/27 16:13
 */
public interface WeixinUrl {

    String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

    String GRANT_TYPE = "client_credential";
}
