package com.food.api.entity;

import com.food.api.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 美食管理实体类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Foods extends BaseEntity {

    /**
    * 主键ID
    */
    private Long id;
    /**
    * 美食名称
    */
    private String foodName;
    /**
    * 美食简介
    */
    private String foodInfo;
    /**
    * 美食详情
    */
    private String foodContent;
    /**
    * 封面图片
    */
    private String bannerImg;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
    * 美食分类
    */
    private Long typeId;
    /**
    * 口味
    */
    private Long tasteId;
    /**
    * 详细图片
    */
    private String imgs;
    /**
    * 用户编号
    */
    private Long userId;
    /**
    * 状态
    */
    private Integer status;


}
