package com.tian.backend.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tian.backend.user.model.Staff;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;


/**
 * @author muyang.tian
 * @date 2021/4/19 10:53
 */
public interface StaffService extends IService<Staff> {

    /**
     * <h2></h2>
     *
     * @param creating 创建对象
     * @return 创建对象
     */
    Staff create(Staff creating);

    /**
     * <h2></h2>
     *
     * @param id       员工ID
     * @param updating 更新的属性
     * @return 更新后的员工
     */
    Staff update(Long id, Staff updating);

    /**
     * <h2></h2>
     *
     * @param pageRequest 分页
     * @param keywords    关键字
     * @param state       状态
     * @return 分页对象
     */
    Page<Staff> page(Page<Staff> pageRequest, String keywords, String state);

    void exportStaff(HttpServletResponse response);

    BufferedImage createCaptcha(String uuid);

    void importStaff(MultipartFile file);

    void updateName(String name, String no);
}
