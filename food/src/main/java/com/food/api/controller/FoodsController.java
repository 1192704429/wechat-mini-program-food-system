package com.food.api.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.food.api.common.BaseController;
import com.food.api.common.BaseSelectEntity;
import com.food.api.common.ReturnMsg;
import com.food.api.dto.*;
import com.food.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: 美食管理对外接口层
 * @author: wuming
 * @create: 2025.02.11 16:40:23
 */
@RestController
@RequestMapping("/foods")
public class FoodsController extends BaseController<FoodsService, FoodsDTO> {

    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private TasteService tasteService;
    @Autowired
    private UsersService usersService;

    @Autowired
    private FoodIngredientsService foodIngredientsService;

    @Autowired
    private IngredientsService ingredientsService;

    @Override
    @Resource(name = "foodsService")
    protected void setBaseService(FoodsService foodsService) {
        this.baseService = foodsService;
    }

    /**
     * 默认分页查询
     *
     * @param foodsDTO
     * @return
     */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody FoodsDTO foodsDTO) {
        Page<FoodsDTO> dtoPage = baseService.basePage(foodsDTO);
        return ReturnMsg.ok(dtoPage);
    }


    @PostMapping("/search")
    public ReturnMsg search(@RequestBody FoodsDTO foodsDTO) {
        List<IngredientsDTO> ingredientsDTOS = foodsDTO.getIngredientsDTOS();
        String innIdStr = ingredientsDTOS.stream().map(item -> item.getId().toString()).collect(Collectors.toList()).stream().collect(Collectors.joining(","));
        List<FoodIngredientsDTO> ingredientsDTOS1 = baseService.findInInn(innIdStr, ingredientsDTOS.size());
        if (CollectionUtil.isEmpty(ingredientsDTOS1)) return ReturnMsg.ok(new ArrayList<>());
        List<Long> collected = ingredientsDTOS1.stream().map(FoodIngredientsDTO::getFoodId).collect(Collectors.toList());
        LambdaQueryWrapper<FoodsDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(FoodsDTO::getId, collected);
        List<FoodsDTO> foodsDTOS1 = this.baseService.list(queryWrapper);
        if (CollectionUtil.isEmpty(foodsDTOS1)) return ReturnMsg.ok(new ArrayList<>());
        String foodName = foodsDTO.getFoodName();
        if (StrUtil.isNotEmpty(foodName)) {
            foodsDTOS1 = foodsDTOS1.stream().filter(item -> item.getFoodName().contains(foodName)).collect(Collectors.toList());
        }
        Long tasteId = foodsDTO.getTasteId();
        if (null != tasteId && tasteId != -1L) {
            foodsDTOS1 = foodsDTOS1.stream().filter(item -> item.getTasteId().equals(tasteId)).collect(Collectors.toList());
        }
        if (CollectionUtil.isEmpty(foodsDTOS1)) return ReturnMsg.ok(new ArrayList<>());

        List<Long> collect = foodsDTOS1.stream().map(FoodsDTO::getId).collect(Collectors.toList());
        FoodsDTO foodsDTO1 = new FoodsDTO();
        foodsDTO1.setIds(collect);
        foodsDTO1.setStatus(1);
        return ReturnMsg.ok(baseService.findList(foodsDTO1));
    }


    /**
     * 根据XML中的sql语句进行查询
     *
     * @param foodsDTO
     * @return
     */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody FoodsDTO foodsDTO) {
        List<FoodsDTO> baseServiceList = baseService.findList(foodsDTO);
        if (foodsDTO.getId() != null) {
            handleFoodIngredientsDTO(baseServiceList);
        }
        return ReturnMsg.ok(baseServiceList);
    }

    public void handleFoodIngredientsDTO(List<FoodsDTO> baseServiceList) {
        for (FoodsDTO dto : baseServiceList) {
            LambdaQueryWrapper<FoodIngredientsDTO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(FoodIngredientsDTO::getFoodId, dto.getId());
            List<FoodIngredientsDTO> foodIngredientsDTOList = foodIngredientsService.list(queryWrapper);
            if (CollectionUtil.isNotEmpty(foodIngredientsDTOList)) {
                LambdaQueryWrapper<IngredientsDTO> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1
                        .in(IngredientsDTO::getId, foodIngredientsDTOList.stream().map(FoodIngredientsDTO::getIngredientsId).collect(Collectors.toList()))
                        .orderByAsc(IngredientsDTO::getType);
                List<IngredientsDTO> ingredientsDTOS = ingredientsService.list(queryWrapper1);
                dto.setIngredientsDTOS(ingredientsDTOS);
                Map<Integer, List<IngredientsDTO>> integerListMap = ingredientsDTOS.stream().collect(Collectors.groupingBy(IngredientsDTO::getType));
                dto.setIngredients1(integerListMap.get(1));
                dto.setIngredients2(integerListMap.get(2));
            }
        }
    }

    @Override
    protected void beforeSave(FoodsDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

    @GetMapping("/index")
    public ReturnMsg indexFood() {
        FoodsDTO foodsDTO = new FoodsDTO();
        foodsDTO.setStatus(1);
        foodsDTO.setCurrent(1);
        foodsDTO.setPageSize(6);
        return ReturnMsg.ok(baseService.findList(foodsDTO));
    }


    @GetMapping("/all/foodType")
    public ReturnMsg allFoodType() {
        List<BaseSelectEntity> list = new ArrayList<>();
        List<FoodTypeDTO> dtos = foodTypeService.list();
        for (FoodTypeDTO dto : dtos) {
            BaseSelectEntity baseSelectEntity = new BaseSelectEntity();
            baseSelectEntity.setKey(dto.getId());
            baseSelectEntity.setValue(dto.getTypeName());
            list.add(baseSelectEntity);
        }
        return ReturnMsg.ok(list);
    }

    @GetMapping("/all/taste")
    public ReturnMsg allTaste() {
        List<BaseSelectEntity> list = new ArrayList<>();
        List<TasteDTO> dtos = tasteService.list();
        for (TasteDTO dto : dtos) {
            BaseSelectEntity baseSelectEntity = new BaseSelectEntity();
            baseSelectEntity.setKey(dto.getId());
            baseSelectEntity.setValue(dto.getTasteName());
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

