package com.tian.backend.user.manager;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tian.backend.common.util.ExcelUtils;
import com.tian.backend.user.common.CommonEnum;
import com.tian.backend.user.model.Staff;
import com.tian.backend.user.repository.StaffRepository;
import com.tian.backend.user.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author muyang.tian
 * @date 2021/4/19 10:53
 */
@Slf4j
@Service
public class StaffManager extends ServiceImpl<StaffRepository,Staff> implements StaffService {

    @Override
    public Page<Staff> page(Page<Staff> pageRequest, String keywords, String state) {
        return baseMapper.page(pageRequest,keywords,state);
    }

    @Override
    public void exportStaff(HttpServletResponse response){
        List<Staff> list = baseMapper.selectList(null);
        String[] headerName = {"昵称","登录名","工号","生日","状态"};
        String[] headerKey = {"nickName","loginName","no","birthday","state"};
        String name = "全部员工";
        HSSFWorkbook workbook = ExcelUtils.exportExcel(name,headerName,headerKey,list);
        ExcelUtils.exportBrowser(workbook,response,name);
    }

    @Override
    public Staff create(Staff creating) {
        log.info("创建员工信息 : {}", JSON.toJSON(creating));
        Staff exist = baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getNo,creating.getNo()));
        if (exist != null){
            throw new RestClientException("工号已存在");
        }
        creating.setState(CommonEnum.StaffState.WORK.getValue());
        creating.setPassword(DigestUtils.md5Hex(creating.getPassword()));
        baseMapper.insert(creating);
        return baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getLoginName,creating.getLoginName()));
    }

    @Override
    public Staff update(Long id,Staff updating) {
        log.info("更新员工信息 : {}", JSON.toJSON(updating));
        updating.setPassword(DigestUtils.md5Hex(updating.getPassword()));
        baseMapper.updateById(updating);
        return baseMapper.selectOne(new LambdaQueryWrapper<Staff>().eq(Staff::getId,id));
    }

}
