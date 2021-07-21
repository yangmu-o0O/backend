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
     * @param creating a
     * @return a
     */
    Staff create(Staff creating);

    /**
     * <h2></h2>
     *
     * @param id       a
     * @param updating a
     * @return a
     */
    Staff update(Long id, Staff updating);

    /**
     * <h2></h2>
     *
     * @param pageRequest 1
     * @param keywords    1
     * @param state       1
     * @return 1
     */
    Page<Staff> page(Page<Staff> pageRequest, String keywords, String state);

    void exportStaff(HttpServletResponse response);

    BufferedImage createCaptcha(String uuid);

    void importStaff(MultipartFile file);

    void updateName(String name, String no);
}
