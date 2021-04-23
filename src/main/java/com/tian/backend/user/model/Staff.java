package com.tian.backend.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <h2>员工实体类</h2>
 *
 * @Author muyang.tian
 * @Date 2021/4/16 17:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("staffs")
@JsonRootName("staff")
public class Staff extends BaseEntity{

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 登录名
     */
    private String loginName;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime birthday;

    /**
     * 状态
     */
    private String state;
}
