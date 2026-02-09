package com.food.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.food.api.dto.IngredientsDTO;
import java.util.List;
/**
* @description: 食材管理业务层接口
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
public interface IngredientsService extends IService<IngredientsDTO> {

    /**
    * 默认分页方法
    * @param ingredientsDTO
    * @return
    */
    Page<IngredientsDTO> basePage(IngredientsDTO ingredientsDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param ingredientsDTO
    * @return
    */
    List<IngredientsDTO> findList(IngredientsDTO ingredientsDTO);

}
