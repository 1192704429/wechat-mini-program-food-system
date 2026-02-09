package com.food.api.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.food.api.entity.FoodIngredients;
import lombok.Data;
import lombok.ToString;

/**
 * @description: 美食食材关联数据传输层
 * @author: wuming
 * @create: 2025.02.11 16:40:23
 */
@Data
@ToString
@TableName("food_ingredients")
public class FoodIngredientsDTO extends FoodIngredients {

    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private Integer type;

}
