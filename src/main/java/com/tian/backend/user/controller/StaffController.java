package com.tian.backend.user.controller;

import com.alibaba.fastjson.JSON;
import com.tian.backend.user.model.Staff;
import com.tian.backend.user.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.List;

/**
 * <h2>员工</h2>
 *
 * @author muyang.tian
 * @date 2021/4/19 10:52
 */
@Slf4j
@RestController
@RequestMapping("/staff")
@Scope(WebApplicationContext.SCOPE_REQUEST)
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

    @PutMapping("/{id}")
    public Staff update(@PathVariable Long id,@RequestBody Staff updating){
        Staff staff = service.getById(id);
        log.info("{}",JSON.toJSON(staff));
        Staff updated = service.update(updating);
        log.info("{}",JSON.toJSON(updated));
        return updated;
    }
}
