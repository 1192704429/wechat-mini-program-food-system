package com.food.api.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.food.api.entity.Ingredients;
import lombok.Data;
import lombok.ToString;

/**
 * @description: 食材管理数据传输层
 * @author: wuming
 * @create: 2025.02.11 16:40:23
 */
@Data
@ToString
@TableName("ingredients")
public class IngredientsDTO extends Ingredients {

    @TableField(exist = false)
    private Boolean isSelect;

    @TableField(exist = false)
    private Boolean isSearch;

}
