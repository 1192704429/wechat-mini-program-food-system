package com.food.api.entity;

import com.food.api.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 美食评价实体类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodComment extends BaseEntity {

    /**
    * 主键ID
    */
    private Long id;
    /**
    * 评价内容
    */
    private String content;
    /**
    * 所属用户
    */
    private Long userId;
    /**
    * 所属美食
    */
    private Long foodsId;
    /**
    * 评分
    */
    private String score;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
    * 回复人编号
    */
    private Long toUserId;
    /**
    * 父级评论编号
    */
    private Long parentId;


}
