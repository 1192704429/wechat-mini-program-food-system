package com.food.api.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.food.api.entity.Foods;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @description: 美食管理数据传输层
 * @author: wuming
 * @create: 2025.02.11 16:40:23
 */
@Data
@ToString
@TableName("foods")
public class FoodsDTO extends Foods {

    /**
     * 分类标题
     */
    @TableField(exist = false)
    private String typeName;
    /**
     * 口味名称
     */
    @TableField(exist = false)
    private String tasteName;
    /**
     * 昵称
     */
    @TableField(exist = false)
    private String nickName;
    /**
     * 头像
     */
    @TableField(exist = false)
    private String headImg;

    @TableField(exist = false)
    private List<IngredientsDTO> ingredientsDTOS;

    @TableField(exist = false)
    private List<IngredientsDTO> ingredients1;

    @TableField(exist = false)
    private List<IngredientsDTO> ingredients2;

    @TableField(exist = false)
    private List<Long> ids;

}
