package com.tian.backend.api.config;

/**
 * @author muyang.tian
 * @date 2021/4/29 11:08
 */
public interface ApiUrl {

    /**
     * 百度坐标转换api
     */
    String ADDRESS_URL = "http://api.map.baidu.com/geocoding/v3/?address={address}&output=json&ak={ak}";

    /**
     * 微信获取access_token的api
     */
    String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    /**
     * 微信获取access_token的grant_type
     */
    String GRANT_TYPE = "client_credential";

    /**
     * 和风天气获取当前天气
     */
    String NOW_WEATHER_URL = "https://devapi.qweather.com/v7/weather/now?key={key}&location={location}&gzip=n";

    /**
     * 和风天气获取三天天气
     */
    String THREE_DAY_WEATHER_URL = "https://devapi.qweather.com/v7/weather/3d?key={key}&location={location}&gzip=n";

    /**
     * 土味情话
     */
    String LOVE_TALK = "https://chp.shadiao.app/api.php";
}
