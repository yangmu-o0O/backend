package com.tian.backend.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tian.backend.user.model.Staff;
import org.springframework.stereotype.Repository;


/**
 * @author muyang.tian
 * @date 2021/4/19 10:52
 */
@Repository
public interface StaffRepository extends BaseMapper<Staff> {


    Page<Staff> page(Page<Staff> pageRequest, String keywords, String state);

}
