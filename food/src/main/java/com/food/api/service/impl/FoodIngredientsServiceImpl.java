package com.food.api.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.food.api.dao.FoodIngredientsDao;
import com.food.api.dto.FoodIngredientsDTO;
import com.food.api.service.FoodIngredientsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 美食食材关联业务层实现类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Service("foodIngredientsService")
public class FoodIngredientsServiceImpl extends ServiceImpl<FoodIngredientsDao, FoodIngredientsDTO> implements FoodIngredientsService {

    @Override
    public Page<FoodIngredientsDTO> basePage(FoodIngredientsDTO foodIngredientsDTO) {
        if (null != foodIngredientsDTO.getCurrent() && null != foodIngredientsDTO.getPageSize())
            foodIngredientsDTO.setCurrent((foodIngredientsDTO.getCurrent() - 1) * foodIngredientsDTO.getPageSize());
        List<FoodIngredientsDTO> list = this.baseMapper.findList(foodIngredientsDTO);
        int count = this.baseMapper.findPageCount(foodIngredientsDTO);
        Page<FoodIngredientsDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<FoodIngredientsDTO> findList(FoodIngredientsDTO foodIngredientsDTO) {
        return this.baseMapper.findList(foodIngredientsDTO);
    }

}
