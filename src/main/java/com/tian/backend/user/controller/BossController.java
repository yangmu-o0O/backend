package com.tian.backend.user.controller;

import com.tian.backend.user.model.Boss;
import com.tian.backend.user.service.BossService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <h2> </h2>
 *
 * @author muyang.tian on 2021/6/24 7:34 下午.
 */
@Slf4j
@RequestMapping("/boss")
@RestController
public class BossController {

    @Resource
    private BossService service;

    @GetMapping
    public List<Boss> list(){
        return service.list();
    }

}
