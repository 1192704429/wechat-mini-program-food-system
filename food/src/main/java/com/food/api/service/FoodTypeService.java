package com.food.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.food.api.dto.FoodTypeDTO;
import java.util.List;
/**
* @description: 美食分类业务层接口
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
public interface FoodTypeService extends IService<FoodTypeDTO> {

    /**
    * 默认分页方法
    * @param foodTypeDTO
    * @return
    */
    Page<FoodTypeDTO> basePage(FoodTypeDTO foodTypeDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param foodTypeDTO
    * @return
    */
    List<FoodTypeDTO> findList(FoodTypeDTO foodTypeDTO);

}
