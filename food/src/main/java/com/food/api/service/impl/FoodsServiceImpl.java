package com.food.api.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.food.api.dao.FoodsDao;
import com.food.api.dto.FoodIngredientsDTO;
import com.food.api.dto.FoodsDTO;
import com.food.api.dto.IngredientsDTO;
import com.food.api.service.FoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 美食管理业务层实现类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Service("foodsService")
public class FoodsServiceImpl extends ServiceImpl<FoodsDao, FoodsDTO> implements FoodsService {

    @Override
    public Page<FoodsDTO> basePage(FoodsDTO foodsDTO) {
        if (null != foodsDTO.getCurrent() && null != foodsDTO.getPageSize())
            foodsDTO.setCurrent((foodsDTO.getCurrent() - 1) * foodsDTO.getPageSize());
        List<FoodsDTO> list = this.baseMapper.findList(foodsDTO);
        int count = this.baseMapper.findPageCount(foodsDTO);
        Page<FoodsDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<FoodsDTO> findList(FoodsDTO foodsDTO) {
        return this.baseMapper.findList(foodsDTO);
    }

    @Override
    public List<FoodIngredientsDTO> findInInn(String innIdStr, int size) {
        return this.baseMapper.findInInn( innIdStr,  size);
    }

}
