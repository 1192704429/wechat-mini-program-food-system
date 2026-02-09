package com.food.api.entity;

import com.food.api.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 食材管理实体类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ingredients extends BaseEntity {

    /**
    * 食材编号
    */
    private Long id;
    /**
    * 食材名称
    */
    private String name;
    /**
    * 图片
    */
    private String img;
    /**
    * 备注
    */
    private String info;
    /**
    * 类型
    */
    private Integer type;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
