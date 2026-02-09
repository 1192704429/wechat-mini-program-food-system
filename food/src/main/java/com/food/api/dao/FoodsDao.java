package com.food.api.dao;

import com.food.api.common.BaseDao;
import com.food.api.dto.FoodIngredientsDTO;
import com.food.api.dto.FoodsDTO;
import com.food.api.dto.IngredientsDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @description: 美食管理持久层
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
public interface FoodsDao extends BaseDao<FoodsDTO> {


    @Select("SELECT food_id FROM food_ingredients WHERE ingredients_id IN (${innIdStr}) GROUP BY food_id HAVING COUNT(DISTINCT ingredients_id) = #{size}")
    List<FoodIngredientsDTO> findInInn(@Param("innIdStr") String innIdStr, @Param("size") int size);
}
