package com.food.api.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.food.api.common.BaseController;
import com.food.api.common.BaseSelectEntity;
import com.food.api.common.ReturnMsg;
import com.food.api.dto.FoodIngredientsDTO;
import com.food.api.dto.FoodsDTO;
import com.food.api.dto.IngredientsDTO;
import com.food.api.service.FoodIngredientsService;
import com.food.api.service.FoodsService;
import com.food.api.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 美食食材关联对外接口层
 * @author: wuming
 * @create: 2025.02.11 16:40:23
 */
@RestController
@RequestMapping("/foodIngredients")
public class FoodIngredientsController extends BaseController<FoodIngredientsService, FoodIngredientsDTO> {

    @Autowired
    private FoodsService foodsService;
    @Autowired
    private IngredientsService ingredientsService;

    @Override
    @Resource(name = "foodIngredientsService")
    protected void setBaseService(FoodIngredientsService foodIngredientsService) {
        this.baseService = foodIngredientsService;
    }

    /**
     * 默认分页查询
     *
     * @param foodIngredientsDTO
     * @return
     */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody FoodIngredientsDTO foodIngredientsDTO) {
        return ReturnMsg.ok(baseService.basePage(foodIngredientsDTO));
    }

    /**
     * 根据XML中的sql语句进行查询
     *
     * @param foodIngredientsDTO
     * @return
     */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody FoodIngredientsDTO foodIngredientsDTO) {
        return ReturnMsg.ok(baseService.findList(foodIngredientsDTO));
    }

    @Override
    protected void beforeSave(FoodIngredientsDTO entity) throws Exception {
        if (exist(entity.getFoodId(), entity.getIngredientsId())) {
            throw new Exception("食材已添加，不能重复添加");
        }
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }


    @GetMapping("/getIngredientsByUserId/{foodId}")
    public ReturnMsg getIngredientsByFoodId(@PathVariable Long foodId) {
        LambdaQueryWrapper<FoodIngredientsDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FoodIngredientsDTO::getFoodId, foodId);
        List<FoodIngredientsDTO> foodIngredientsDTOList = baseService.list(queryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Long> userSelectMain = new ArrayList<>();
        List<Long> userSelectNext = new ArrayList<>();
        List<IngredientsDTO> ingredientsDTOS = new ArrayList<>();
        // 美食已选择的食材
        if (CollectionUtil.isNotEmpty(foodIngredientsDTOList)) {
            LambdaQueryWrapper<IngredientsDTO> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.in(IngredientsDTO::getId, foodIngredientsDTOList.stream().map(FoodIngredientsDTO::getIngredientsId).collect(Collectors.toList())).orderByAsc(IngredientsDTO::getType);
            ingredientsDTOS = ingredientsService.list(queryWrapper1);
            Map<Integer, List<IngredientsDTO>> integerListMap = ingredientsDTOS.stream().collect(Collectors.groupingBy(IngredientsDTO::getType));
            List<IngredientsDTO> ingredientsDTOS1 = integerListMap.get(1);
            List<IngredientsDTO> ingredientsDTOS2 = integerListMap.get(2);
            userSelectMain = ingredientsDTOS1.stream().map(IngredientsDTO::getId).collect(Collectors.toList());
            hashMap.put("userFirstInId", userSelectMain);
            userSelectNext = ingredientsDTOS2.stream().map(IngredientsDTO::getId).collect(Collectors.toList());
            hashMap.put("userTwoInId", userSelectNext);
        } else {
            hashMap.put("userFirstInId", new ArrayList<>());
            hashMap.put("userTwoInId", new ArrayList<>());
        }
        // 全部食材
        List<IngredientsDTO> ingredientsDTOS3 = ingredientsService.list();
        if (CollectionUtil.isNotEmpty(ingredientsDTOS3)) {
            Map<Integer, List<IngredientsDTO>> integerListMap = ingredientsDTOS3.stream().collect(Collectors.groupingBy(IngredientsDTO::getType));
            List<IngredientsDTO> ingredientsDTOS1 = integerListMap.get(1);
            List<IngredientsDTO> ingredientsDTOS2 = integerListMap.get(2);
            if (CollectionUtil.isNotEmpty(ingredientsDTOS1)) {
                hashMap.put("sysFirstInId", ingredientsDTOS1);
                // 处理选择状态
                if (CollectionUtil.isNotEmpty(userSelectMain)) {
                    for (IngredientsDTO ingredientsDTO : ingredientsDTOS1) {
                        ingredientsDTO.setIsSelect(userSelectMain.contains(ingredientsDTO.getId()));
                    }
                }
            }
            if (CollectionUtil.isNotEmpty(ingredientsDTOS2)) {
                hashMap.put("sysTwoInId", ingredientsDTOS2);
                // 处理选择状态
                if (CollectionUtil.isNotEmpty(userSelectNext)) {
                    for (IngredientsDTO ingredientsDTO : ingredientsDTOS2) {
                        ingredientsDTO.setIsSelect(userSelectNext.contains(ingredientsDTO.getId()));
                    }
                }
            }
        } else {
            hashMap.put("sysFirstInId", new ArrayList<>());
            hashMap.put("sysTwoInId", new ArrayList<>());
        }
        hashMap.put("userSelect", ingredientsDTOS);
        return ReturnMsg.ok(hashMap);
    }

    @GetMapping("/getByFoodId/{foodId}")
    public ReturnMsg getByFoodId(@PathVariable Long foodId) {
        LambdaQueryWrapper<FoodIngredientsDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FoodIngredientsDTO::getFoodId, foodId);
        List<FoodIngredientsDTO> foodIngredientsDTOList = baseService.list(queryWrapper);
        List<IngredientsDTO> ingredientsDTOS = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(foodIngredientsDTOList)) {
            LambdaQueryWrapper<IngredientsDTO> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.in(IngredientsDTO::getId, foodIngredientsDTOList.stream().map(FoodIngredientsDTO::getIngredientsId).collect(Collectors.toList())).orderByAsc(IngredientsDTO::getType);
            ingredientsDTOS = ingredientsService.list(queryWrapper1);
        }
        return ReturnMsg.ok(ingredientsDTOS);
    }


    @PostMapping("/saveUserSelect/{foodId}")
    public ReturnMsg saveUserSelect(@PathVariable("foodId") Long foodId, @RequestBody List<FoodIngredientsDTO> ingredientsDTOS) {
        LambdaQueryWrapper<FoodIngredientsDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FoodIngredientsDTO::getFoodId, foodId);
        this.baseService.remove(queryWrapper);
        this.baseService.saveBatch(ingredientsDTOS);
        return ReturnMsg.ok();
    }


    public boolean exist(Long foodId, Long id) {
        LambdaQueryWrapper<FoodIngredientsDTO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FoodIngredientsDTO::getFoodId, foodId);
        queryWrapper.eq(FoodIngredientsDTO::getIngredientsId, id);
        return baseService.count(queryWrapper) > 0;
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

    @GetMapping("/all/ingredients")
    public ReturnMsg allIngredients() {
        List<BaseSelectEntity> list = new ArrayList<>();
        List<IngredientsDTO> dtos = ingredientsService.list();
        for (IngredientsDTO dto : dtos) {
            BaseSelectEntity baseSelectEntity = new BaseSelectEntity();
            baseSelectEntity.setKey(dto.getId());
            baseSelectEntity.setValue(dto.getName());
            list.add(baseSelectEntity);
        }
        return ReturnMsg.ok(list);
    }

}

