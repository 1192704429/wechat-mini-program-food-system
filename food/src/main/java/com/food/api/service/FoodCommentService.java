package com.food.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.food.api.dto.FoodCommentDTO;
import java.util.List;
/**
* @description: 美食评价业务层接口
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
public interface FoodCommentService extends IService<FoodCommentDTO> {

    /**
    * 默认分页方法
    * @param foodCommentDTO
    * @return
    */
    Page<FoodCommentDTO> basePage(FoodCommentDTO foodCommentDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param foodCommentDTO
    * @return
    */
    List<FoodCommentDTO> findList(FoodCommentDTO foodCommentDTO);

}
