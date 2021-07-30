package com.tian.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <h2>对某些请求做特殊处理 切面实现</h2>
 *
 * @author muyang.tian on 2021/6/30 11:32 上午.
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * ..表示包及子包 该方法代表controller层的所有方法
     *
     */
    @Pointcut("execution(public * com.tian.backend.user.controller.*.*(..))" +
    "")
    public void controllerMethod() {
        //不需要方法内做任何事
    }

    @Before("controllerMethod()")
    public void aspect(){
        //暂时没有啥事要做
    }
}
