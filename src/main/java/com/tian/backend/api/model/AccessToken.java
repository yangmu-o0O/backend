package com.tian.backend.api.model;

import lombok.Data;

/**
 * <h2>AccessToken实体类</h2>
 * 不需要集成baseModel 完全没必要
 * @author muyang.tian
 * @date 2021/4/28 10:15
 */
@Data
public class AccessToken {

    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private Integer expiresIn;
}
