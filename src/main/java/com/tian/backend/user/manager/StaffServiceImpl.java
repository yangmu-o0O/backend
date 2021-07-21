package com.tian.backend.user.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.tian.backend.common.util.ExcelUtils;
import com.tian.backend.user.common.CommonEnum;
import com.tian.backend.user.model.Staff;
import com.tian.backend.user.repository.StaffRepository;
import com.tian.backend.user.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author muyang.tian
 * @date 2021/4/19 10:53
 */
@Slf4j
@Service
public class StaffServiceImpl extends ServiceImpl<StaffRepository, Staff> implements StaffService, InitializingBean {

    @Resource
    private Producer producer;
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void afterPropertiesSet() {
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setKeySerializer(StringRedisSerializer.UTF_8);
        redisTemplate.setHashKeySerializer(StringRedisSerializer.UTF_8);
    }

    @Override
    public Page<Staff> page(Page<Staff> pageRequest, String keywords, String state) {
        return baseMapper.page(pageRequest, keywords, state);
    }

    @Override
    public void exportStaff(HttpServletResponse response) {
        List<Staff> list = baseMapper.selectList(null);
        String[] headerName = {"昵称", "登录名", "工号", "生日", "状态"};
        String[] headerKey = {"nickName", "loginName", "no", "birthday", "state"};
        String name = "全部员工";
        HSSFWorkbook workbook = ExcelUtils.exportExcel(name, headerName, headerKey, list);
        ExcelUtils.exportBrowser(workbook, response, name);
    }

    @Override
    public BufferedImage createCaptcha(String uuid) {
        String code = producer.createText();
        redisTemplate.opsForValue().set(uuid, code, 1, TimeUnit.MINUTES);
        return producer.createImage(code);
    }

    @Override
    public void importStaff(MultipartFile file) {
        Assert.notNull(file, "文件不能为空! (艹皿艹)!");
    }

    @Override
    public void updateName(String name, String no) {
        baseMapper.updateName(name, no);
    }

    @Override
    public Staff create(Staff creating) {
        log.info("创建员工信息 : {}", JSON.toJSON(creating));
        Staff exist = baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getNo, creating.getNo()));
        if (exist != null) {
            throw new RestClientException("工号已存在");
        }
        creating.setState(CommonEnum.StaffState.WORK.getValue());
        creating.setPassword(DigestUtils.md5Hex(creating.getPassword()));
        baseMapper.insert(creating);
        return baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getLoginName, creating.getLoginName()));
    }

    @Override
    public Staff update(Long id, Staff updating) {
        log.info("更新员工信息 : {}", JSON.toJSON(updating));
        updating.setPassword(DigestUtils.md5Hex(updating.getPassword()));
        baseMapper.updateById(updating);
        return baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getId, id));
    }

}
