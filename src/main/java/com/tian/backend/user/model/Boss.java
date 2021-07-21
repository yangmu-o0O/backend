package com.tian.backend.user.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <h2> </h2>
 *
 * @author muyang.tian on 2021/6/24 7:28 下午.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("boss")
public class Boss extends BaseModel{

    /**
     * 老板名称
     */
    private String name;


    /**
     * 员工
     */
    private List<Staff> staffs;
}
