package com.food.api.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.food.api.entity. UserCollect;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @description: 收藏&点赞数据传输层
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Data
@ToString
@TableName("user_collect")
public class UserCollectDTO extends UserCollect {

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
    /**
    * 美食名称
    */
    @TableField(exist = false)
    private String foodName;
    /**
    * 封面图片
    */
    @TableField(exist = false)
    private String bannerImg;

}
