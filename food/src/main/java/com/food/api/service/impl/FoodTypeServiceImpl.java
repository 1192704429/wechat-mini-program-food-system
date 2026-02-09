package com.food.api.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.food.api.dao.FoodTypeDao;
import com.food.api.dto.FoodTypeDTO;
import com.food.api.service.FoodTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 美食分类业务层实现类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Service("foodTypeService")
public class FoodTypeServiceImpl extends ServiceImpl<FoodTypeDao, FoodTypeDTO> implements FoodTypeService {

    @Override
    public Page<FoodTypeDTO> basePage(FoodTypeDTO foodTypeDTO) {
        if (null != foodTypeDTO.getCurrent() && null != foodTypeDTO.getPageSize())
            foodTypeDTO.setCurrent((foodTypeDTO.getCurrent() - 1) * foodTypeDTO.getPageSize());
        List<FoodTypeDTO> list = this.baseMapper.findList(foodTypeDTO);
        int count = this.baseMapper.findPageCount(foodTypeDTO);
        Page<FoodTypeDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<FoodTypeDTO> findList(FoodTypeDTO foodTypeDTO) {
        return this.baseMapper.findList(foodTypeDTO);
    }

}
