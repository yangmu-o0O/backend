package com.tian.backend.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tian.backend.user.model.Boss;
import com.tian.backend.user.model.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author muyang.tian
 * @date 2021/4/19 10:52
 */
@Repository
public interface BossRepository extends BaseMapper<Boss> {

    /**
     * <h2></h2>
     *
     * @return 老板
     */
    List<Boss> list();
}
