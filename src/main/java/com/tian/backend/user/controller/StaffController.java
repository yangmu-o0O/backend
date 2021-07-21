package com.tian.backend.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tian.backend.user.model.Staff;
import com.tian.backend.user.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
     * <h2>分页查询员工</h2>
     * @param keywords 关键字
     * @param state 状态
     * @param pageRequest 分页对象
     * @return 分页对象
     */
    @GetMapping
    public Page<Staff> page(Page<Staff> pageRequest,
                            @RequestParam(required = false) String keywords,
                            @RequestParam(required = false) String state){
        log.debug("searching all staff with keywords:{}",keywords);
        Page<Staff> staffList = service.page(pageRequest,keywords,state);
        log.debug("searched all staff size:{}",staffList.getRecords().size());
        return staffList;
    }

    @PostMapping
    public Staff create(@RequestBody Staff creating){
        log.debug("creating staff with {}",JSON.toJSON(creating));
        Staff created = service.create(creating);
        log.debug("created staff with {}",JSON.toJSON(created));
        return created;
    }

    @PutMapping("/{id}")
    public Staff update(@PathVariable Long id,@RequestBody Staff updating){
        log.debug("{}",JSON.toJSON(updating));
        Staff updated = service.update(id,updating);
        log.debug("{}",JSON.toJSON(updated));
        return updated;
    }

    /**
     * <h2>导出员工信息</h2>
     * @param response 返回表格响应
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        service.exportStaff(response);
    }

    /**
     * <h2>导入员工信息</h2>
     * @param file 导入excel文件
     */
    @GetMapping("/import")
    public void importStaff(MultipartFile file){
        service.importStaff(file);
    }

    /**
     * <h2>获取图片验证码</h2>
     * @param response 返回图片响应
     * @param uuid 用于存放验证码的RedisKey
     */
    @GetMapping("/captcha.jpg")
    public void imageCaptcha(HttpServletResponse response,
                             @RequestParam("uuid")String uuid) throws IOException{
        log.info("获取图片验证码");
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //获取图片验证码
        BufferedImage image = service.createCaptcha(uuid);
        //jdk1.7语法糖try-with-resource 自动关闭连接
        try(ServletOutputStream out = response.getOutputStream()){
            ImageIO.write(image, "jpg", out);
        }
    }

    @PutMapping("/update_name/{no}")
    public void updateName(@RequestParam String name,@PathVariable String no){
        service.updateName(name,no);
    }
}
