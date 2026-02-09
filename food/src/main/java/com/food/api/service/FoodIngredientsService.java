package com.food.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.food.api.dto.FoodIngredientsDTO;
import java.util.List;
/**
* @description: 美食食材关联业务层接口
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
public interface FoodIngredientsService extends IService<FoodIngredientsDTO> {

    /**
    * 默认分页方法
    * @param foodIngredientsDTO
    * @return
    */
    Page<FoodIngredientsDTO> basePage(FoodIngredientsDTO foodIngredientsDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param foodIngredientsDTO
    * @return
    */
    List<FoodIngredientsDTO> findList(FoodIngredientsDTO foodIngredientsDTO);

}
