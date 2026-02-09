package com.food.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.food.api.common.BaseController;
import com.food.api.common.ReturnMsg;
import com.food.api.dto.UsersDTO;
import com.food.api.service.UsersService;
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
 * @description: 用户管理对外接口层
 * @author: wuming
 * @create: 2025.02.11 16:40:23
 */
@RestController
@RequestMapping("/users")
public class UsersController extends BaseController<UsersService, UsersDTO> {


    @Override
    @Resource(name = "usersService")
    protected void setBaseService(UsersService usersService) {
        this.baseService = usersService;
    }

    /**
     * 默认分页查询
     *
     * @param usersDTO
     * @return
     */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody UsersDTO usersDTO) {
        return ReturnMsg.ok(baseService.basePage(usersDTO));
    }

    /**
     * 根据XML中的sql语句进行查询
     *
     * @param usersDTO
     * @return
     */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody UsersDTO usersDTO) {
        return ReturnMsg.ok(baseService.findList(usersDTO));
    }

    @Override
    protected void beforeSave(UsersDTO entity) throws Exception {
        String userName = entity.getUserName();
        LambdaQueryWrapper<UsersDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UsersDTO::getUserName, userName);
        int count = baseService.count(queryWrapper);
        if (count > 0) {
            throw new Exception("用户名已存在！");
        }

        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

}

