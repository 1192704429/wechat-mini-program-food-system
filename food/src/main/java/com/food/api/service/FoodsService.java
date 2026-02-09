package com.food.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.food.api.dto.FoodIngredientsDTO;
import com.food.api.dto.FoodsDTO;
import com.food.api.dto.IngredientsDTO;

import java.util.List;
/**
* @description: 美食管理业务层接口
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
public interface FoodsService extends IService<FoodsDTO> {

    /**
    * 默认分页方法
    * @param foodsDTO
    * @return
    */
    Page<FoodsDTO> basePage(FoodsDTO foodsDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param foodsDTO
    * @return
    */
    List<FoodsDTO> findList(FoodsDTO foodsDTO);

    List<FoodIngredientsDTO> findInInn(String innIdStr, int size);
}
