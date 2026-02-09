package com.food.api.controller;

import com.food.api.common.BaseController;
import com.food.api.common.ReturnMsg;
import com.food.api.dto.FoodTypeDTO;
import com.food.api.service.FoodTypeService;
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
* @description: 美食分类对外接口层
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@RestController
@RequestMapping("/foodType")
public class FoodTypeController extends BaseController<FoodTypeService, FoodTypeDTO> {


    @Override
    @Resource(name = "foodTypeService")
    protected void setBaseService(FoodTypeService foodTypeService) {
        this.baseService = foodTypeService;
    }

    /**
    * 默认分页查询
    * @param foodTypeDTO
    * @return
    */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody FoodTypeDTO foodTypeDTO) {
        return ReturnMsg.ok(baseService.basePage(foodTypeDTO));
    }

    /**
    * 根据XML中的sql语句进行查询
    * @param foodTypeDTO
    * @return
    */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody FoodTypeDTO foodTypeDTO) {
        return ReturnMsg.ok(baseService.findList(foodTypeDTO));
    }

    @Override
    protected void beforeSave(FoodTypeDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

}

