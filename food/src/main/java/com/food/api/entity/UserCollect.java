package com.food.api.entity;

import com.food.api.common.BaseEntity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
* @description: 收藏&点赞实体类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserCollect extends BaseEntity {

    /**
    * 编号
    */
    private Long id;
    /**
    * 美食名称
    */
    private Long foodId;
    /**
    * 所属用户
    */
    private Long userId;
    /**
    * 类型
    */
    private Integer typeId;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


}
