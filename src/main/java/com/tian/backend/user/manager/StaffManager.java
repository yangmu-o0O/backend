package com.tian.backend.user.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tian.backend.user.common.CommonEnum;
import com.tian.backend.user.model.Staff;
import com.tian.backend.user.repository.StaffRepository;
import com.tian.backend.user.service.StaffService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author muyang.tian
 * @date 2021/4/19 10:53
 */
@Service
public class StaffManager implements StaffService {

    @Resource
    private StaffRepository baseMapper;

    @Override
    public List<Staff> index() {
        return baseMapper.selectList(null);
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
    public Staff getById(Long id) {
        return baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getId,id));
    }

    @Override
    public Staff update(Staff updating) {
        baseMapper.updateById(updating);
        return baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getLoginName,updating.getLoginName()));
    }
}
