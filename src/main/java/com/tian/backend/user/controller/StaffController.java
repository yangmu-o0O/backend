package com.tian.backend.user.controller;

import com.alibaba.fastjson.JSON;
import com.tian.backend.user.model.Staff;
import com.tian.backend.user.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author muyang.tian
 * @date 2021/4/19 10:52
 */
@Slf4j
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Resource
    private StaffService service;

    /**
     *
     * @return 所有员工
     */
    @GetMapping
    public List<Staff> index(){
        log.info("searching all staff");
        List<Staff> staffList = service.index();
        log.info("searched all staff size:{}",staffList.size());
        return staffList;
    }

    @PostMapping
    public Staff create(@RequestBody Staff creating){
        log.info("creating staff with {}",JSON.toJSON(creating));
        Staff created = service.create(creating);
        log.info("created staff with {}",JSON.toJSON(created));
        return created;
    }

}
