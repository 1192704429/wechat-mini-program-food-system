package com.food.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.food.api.common.BaseController;
import com.food.api.common.BaseSelectEntity;
import com.food.api.common.ReturnMsg;
import com.food.api.dto.FoodsDTO;
import com.food.api.dto.UserCollectDTO;
import com.food.api.dto.UsersDTO;
import com.food.api.service.FoodsService;
import com.food.api.service.UserCollectService;
import com.food.api.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @description: 收藏&点赞对外接口层
 * @author: wuming
 * @create: 2025.02.11 16:40:23
 */
@RestController
@RequestMapping("/userCollect")
public class UserCollectController extends BaseController<UserCollectService, UserCollectDTO> {

    @Autowired
    private FoodsService foodsService;
    @Autowired
    private UsersService usersService;

    @Override
    @Resource(name = "userCollectService")
    protected void setBaseService(UserCollectService userCollectService) {
        this.baseService = userCollectService;
    }

    /**
     * 默认分页查询
     *
     * @param userCollectDTO
     * @return
     */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody UserCollectDTO userCollectDTO) {
        return ReturnMsg.ok(baseService.basePage(userCollectDTO));
    }

    /**
     * 根据XML中的sql语句进行查询
     *
     * @param userCollectDTO
     * @return
     */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody UserCollectDTO userCollectDTO) {
        return ReturnMsg.ok(baseService.findList(userCollectDTO));
    }

    @PostMapping(value = "/removeCollect")
    public ReturnMsg removeCollect(@RequestBody UserCollectDTO userCollectDTO) {
        LambdaQueryWrapper<UserCollectDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(UserCollectDTO::getUserId, userCollectDTO.getUserId())
                .eq(UserCollectDTO::getFoodId, userCollectDTO.getFoodId())
                .eq(UserCollectDTO::getTypeId, userCollectDTO.getTypeId());
        baseService.remove(queryWrapper);
        return ReturnMsg.ok();
    }


    @PostMapping(value = "/checkUserData")
    public ReturnMsg checkUserData(@RequestBody UserCollectDTO userCollectDTO) {
        LambdaQueryWrapper<UserCollectDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserCollectDTO::getFoodId, userCollectDTO.getFoodId()).eq(UserCollectDTO::getUserId, userCollectDTO.getUserId());
        List<UserCollectDTO> collectDTOS = baseService.list(queryWrapper);
        boolean hasCollect = false, hasLike = false;
        for (UserCollectDTO collectDTO : collectDTOS) {
            if (collectDTO.getTypeId() == 2) {
                hasCollect = true;
            }
            if (collectDTO.getTypeId() == 1) {
                hasLike = true;
            }
        }
        HashMap<String, Boolean> hashMap = new HashMap<>();
        hashMap.put("hasCollect", hasCollect);
        hashMap.put("hasLike", hasLike);
        return ReturnMsg.ok(hashMap);
    }


    @Override
    protected void beforeSave(UserCollectDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
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

}

