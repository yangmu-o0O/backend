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

    /**
     * <h2></h2>
     *
     * @param pageRequest a
     * @param keywords    a
     * @param state       a
     * @return a
     */
    Page<Staff> page(Page<Staff> pageRequest, String keywords, String state);

    void updateName(String name, String no);
}
