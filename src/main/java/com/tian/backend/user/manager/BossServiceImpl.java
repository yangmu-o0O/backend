package com.tian.backend.user.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tian.backend.user.model.Boss;
import com.tian.backend.user.repository.BossRepository;
import com.tian.backend.user.service.BossService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author muyang.tian
 * @date 2021/4/19 10:53
 */
@Slf4j
@Service
public class BossServiceImpl extends ServiceImpl<BossRepository, Boss> implements BossService {

    @Override
    public List<Boss> list() {
        return baseMapper.list();
    }
}
