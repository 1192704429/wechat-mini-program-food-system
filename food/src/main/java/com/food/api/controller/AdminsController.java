package com.food.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.food.api.common.BaseController;
import com.food.api.common.ReturnMsg;
import com.food.api.dto.AdminsDTO;
import com.food.api.dto.UsersDTO;
import com.food.api.service.AdminsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import com.food.api.common.BaseSelectEntity;
import cn.hutool.core.collection.CollectionUtil;
import java.util.Date;
/**
* @description: 管理员对外接口层
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@RestController
@RequestMapping("/admins")
public class AdminsController extends BaseController<AdminsService, AdminsDTO> {


    @Override
    @Resource(name = "adminsService")
    protected void setBaseService(AdminsService adminsService) {
        this.baseService = adminsService;
    }

    /**
    * 默认分页查询
    * @param adminsDTO
    * @return
    */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody AdminsDTO adminsDTO) {
        return ReturnMsg.ok(baseService.basePage(adminsDTO));
    }

    /**
    * 根据XML中的sql语句进行查询
    * @param adminsDTO
    * @return
    */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody AdminsDTO adminsDTO) {
        return ReturnMsg.ok(baseService.findList(adminsDTO));
    }

    @Override
    protected void beforeSave(AdminsDTO entity) throws Exception {
        String userName = entity.getUserName();
        LambdaQueryWrapper<AdminsDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminsDTO::getUserName, userName);
        int count = baseService.count(queryWrapper);
        if (count > 0) {
            throw new Exception("用户名已存在！");
        }
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

}

