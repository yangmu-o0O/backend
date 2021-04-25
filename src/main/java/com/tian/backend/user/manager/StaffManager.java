package com.tian.backend.user.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tian.backend.user.common.CommonEnum;
import com.tian.backend.user.model.Staff;
import com.tian.backend.user.repository.StaffRepository;
import com.tian.backend.user.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author muyang.tian
 * @date 2021/4/19 10:53
 */
@Slf4j
@Service
public class StaffManager extends ServiceImpl<StaffRepository,Staff> implements StaffService {

    @Override
    public Page<Staff> page(Page<Staff> pageRequest, String keywords, String state) {
        return baseMapper.page(pageRequest,keywords,state);
    }

    @Override
    public Staff create(Staff creating) {
        Staff exist = baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getNo,creating.getNo()));
        if (exist != null){
            throw new RestClientException("工号已存在");
        }
        creating.setState(CommonEnum.StaffState.WORK.getValue());
        baseMapper.insert(creating);
        return baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getLoginName,creating.getLoginName()));
    }

    @Override
    public Staff update(Long id,Staff updating) {
        baseMapper.updateById(updating);
        return baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getId,id));
    }
}
