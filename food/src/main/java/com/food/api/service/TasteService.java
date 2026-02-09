package com.food.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.food.api.dto.TasteDTO;
import java.util.List;
/**
* @description: 口味管理业务层接口
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
public interface TasteService extends IService<TasteDTO> {

    /**
    * 默认分页方法
    * @param tasteDTO
    * @return
    */
    Page<TasteDTO> basePage(TasteDTO tasteDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param tasteDTO
    * @return
    */
    List<TasteDTO> findList(TasteDTO tasteDTO);

}
