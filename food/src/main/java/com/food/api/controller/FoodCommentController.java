package com.food.api.controller;

import com.food.api.common.BaseController;
import com.food.api.common.ReturnMsg;
import com.food.api.dto.FoodCommentDTO;
import com.food.api.service.FoodCommentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.food.api.common.BaseSelectEntity;
import java.util.ArrayList;
import com.food.api.service.UsersService;
import com.food.api.dto.UsersDTO;
import com.food.api.service.FoodsService;
import com.food.api.dto.FoodsDTO;
import javax.annotation.Resource;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import com.food.api.common.BaseSelectEntity;
import cn.hutool.core.collection.CollectionUtil;
import java.util.Date;
/**
* @description: 美食评价对外接口层
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@RestController
@RequestMapping("/foodComment")
public class FoodCommentController extends BaseController<FoodCommentService, FoodCommentDTO> {

    @Autowired
    private UsersService usersService;
    @Autowired
    private FoodsService foodsService;

    @Override
    @Resource(name = "foodCommentService")
    protected void setBaseService(FoodCommentService foodCommentService) {
        this.baseService = foodCommentService;
    }

    /**
    * 默认分页查询
    * @param foodCommentDTO
    * @return
    */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody FoodCommentDTO foodCommentDTO) {
        return ReturnMsg.ok(baseService.basePage(foodCommentDTO));
    }

    /**
    * 根据XML中的sql语句进行查询
    * @param foodCommentDTO
    * @return
    */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody FoodCommentDTO foodCommentDTO) {
        return ReturnMsg.ok(baseService.findList(foodCommentDTO));
    }

    @Override
    protected void beforeSave(FoodCommentDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

    @GetMapping("/all/users")
    public ReturnMsg allUsers() {
        List<BaseSelectEntity> list = new ArrayList<>();
        List<UsersDTO> dtos = usersService.list();
            for (UsersDTO dto : dtos) {
            BaseSelectEntity baseSelectEntity = new BaseSelectEntity();
            baseSelectEntity.setKey(dto.getId());
            baseSelectEntity.setValue(dto.getNickName());
            list.add(baseSelectEntity);
        }
        return ReturnMsg.ok(list);
    }

    @GetMapping("/all/foods")
    public ReturnMsg allFoods() {
        List<BaseSelectEntity> list = new ArrayList<>();
        List<FoodsDTO> dtos = foodsService.list();
            for (FoodsDTO dto : dtos) {
            BaseSelectEntity baseSelectEntity = new BaseSelectEntity();
            baseSelectEntity.setKey(dto.getId());
            baseSelectEntity.setValue(dto.getFoodName());
            list.add(baseSelectEntity);
        }
        return ReturnMsg.ok(list);
    }

}

