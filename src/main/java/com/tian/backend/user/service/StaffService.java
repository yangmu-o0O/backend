package com.tian.backend.user.service;

import com.tian.backend.user.model.Staff;

import java.util.List;

/**
 * @author muyang.tian
 * @date 2021/4/19 10:53
 */
public interface StaffService {

    List<Staff> index();

    Staff create(Staff creating);

    Staff getById(Long id);

    Staff update(Staff updating);
}
