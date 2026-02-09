package com.food.api.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.food.api.entity. FoodType;
import lombok.Data;
import lombok.ToString;

/**
* @description: 美食分类数据传输层
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Data
@ToString
@TableName("food_type")
public class FoodTypeDTO extends FoodType {


}
