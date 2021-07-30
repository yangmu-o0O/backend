package com.tian.backend.config;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * <h2>打印请求头尾日志 </h2>
 *
 * @author muyang.tian on 2021/7/29 11:42 上午.
 */
public class RequestLoggingFilter extends CommonsRequestLoggingFilter {
    private final ThreadLocal<Long> startAt = new ThreadLocal<>();

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        startAt.set(System.currentTimeMillis());
        logger.info(message);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        if (startAt.get() == null) {
            startAt.set(System.currentTimeMillis());
        }
        long cost = System.currentTimeMillis() - startAt.get();
        startAt.remove();
        logger.info("after Request:" + cost + "ms [" + message);
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return logger.isInfoEnabled();
    }
}
