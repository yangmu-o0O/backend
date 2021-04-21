package com.tian.backend.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author muyang.tian
 * @Date 2021/4/16 17:32
 */
@Data
@TableName("staffs")
public class Staff implements Serializable {

    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 登录名
     */
    private String login;

    /**
     * 密码
     */
    private String password;

    /**
     * 工号
     */
    private Integer no;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 状态
     */
    private String state;

    /**
     * 创建时间
     */
    private LocalDateTime created_at;

    /**
     * 更新时间
     */
    private LocalDateTime updated_at;

    /**
     * 创建人
     */
    private LocalDateTime created_by;

    /**
     * 更新时间
     */
    private LocalDateTime updated_by;
}
