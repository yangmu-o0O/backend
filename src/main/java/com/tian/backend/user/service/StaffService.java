package com.tian.backend.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tian.backend.user.model.Staff;


/**
 * @author muyang.tian
 * @date 2021/4/19 10:53
 */
public interface StaffService extends IService<Staff> {

    Staff create(Staff creating);

    Staff update(Long id,Staff updating);

    Page<Staff> page(Page<Staff> pageRequest, String keywords, String state);
}
