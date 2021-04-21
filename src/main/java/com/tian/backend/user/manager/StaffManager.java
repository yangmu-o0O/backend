package com.tian.backend.user.manager;

import com.tian.backend.user.model.Staff;
import com.tian.backend.user.repository.StaffRepository;
import com.tian.backend.user.service.StaffService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author muyang.tian
 * @date 2021/4/19 10:53
 */
@Service
public class StaffManager implements StaffService {

    @Resource
    private StaffRepository repository;

    @Override
    public List<Staff> index() {
        return repository.selectList(null);
    }

    @Override
    public Staff create(Staff creating) {
        repository.insert(creating);
        return repository.selectOne(null);
    }
}
