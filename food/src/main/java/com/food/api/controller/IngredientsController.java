package com.food.api.controller;

import com.food.api.common.BaseController;
import com.food.api.common.ReturnMsg;
import com.food.api.dto.IngredientsDTO;
import com.food.api.service.IngredientsService;
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
* @description: 食材管理对外接口层
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@RestController
@RequestMapping("/ingredients")
public class IngredientsController extends BaseController<IngredientsService, IngredientsDTO> {


    @Override
    @Resource(name = "ingredientsService")
    protected void setBaseService(IngredientsService ingredientsService) {
        this.baseService = ingredientsService;
    }

    /**
    * 默认分页查询
    * @param ingredientsDTO
    * @return
    */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody IngredientsDTO ingredientsDTO) {
        return ReturnMsg.ok(baseService.basePage(ingredientsDTO));
    }

    /**
    * 根据XML中的sql语句进行查询
    * @param ingredientsDTO
    * @return
    */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody IngredientsDTO ingredientsDTO) {
        return ReturnMsg.ok(baseService.findList(ingredientsDTO));
    }

    @Override
    protected void beforeSave(IngredientsDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

}

