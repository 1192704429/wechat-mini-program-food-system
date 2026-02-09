package com.food.api.entity;

import com.food.api.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 公告信息实体类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice extends BaseEntity {

    /**
    * 公告编号
    */
    private Long id;
    /**
    * 公告图片
    */
    private String bannerImg;
    /**
    * 公告内容
    */
    private String content;
    /**
    * 公告简介
    */
    private String info;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
    * 公告标题
    */
    private String title;
    /**
    * 所属分类
    */
    private Integer type;


}
