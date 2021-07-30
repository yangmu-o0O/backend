package com.tian.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * <h2>这里还可以做其他的事 </h2>
 *
 * @author muyang.tian on 2021/7/29 2:12 下午.
 */
@Configuration
public class WebApplicationConfig {

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter(){
        CommonsRequestLoggingFilter loggingFilter = new RequestLoggingFilter();
        loggingFilter.setIncludeHeaders(false);
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(1024);
        loggingFilter.setAfterMessagePrefix("");
        return loggingFilter;
    }
}
