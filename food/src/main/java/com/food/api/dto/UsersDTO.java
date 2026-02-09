package com.food.api.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.food.api.entity. Users;
import lombok.Data;
import lombok.ToString;

/**
* @description: 用户管理数据传输层
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Data
@ToString
@TableName("users")
public class UsersDTO extends Users {


}
