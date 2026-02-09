package com.food.api.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.food.api.dao.IngredientsDao;
import com.food.api.dto.IngredientsDTO;
import com.food.api.service.IngredientsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 食材管理业务层实现类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Service("ingredientsService")
public class IngredientsServiceImpl extends ServiceImpl<IngredientsDao, IngredientsDTO> implements IngredientsService {

    @Override
    public Page<IngredientsDTO> basePage(IngredientsDTO ingredientsDTO) {
        if (null != ingredientsDTO.getCurrent() && null != ingredientsDTO.getPageSize())
            ingredientsDTO.setCurrent((ingredientsDTO.getCurrent() - 1) * ingredientsDTO.getPageSize());
        List<IngredientsDTO> list = this.baseMapper.findList(ingredientsDTO);
        int count = this.baseMapper.findPageCount(ingredientsDTO);
        Page<IngredientsDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<IngredientsDTO> findList(IngredientsDTO ingredientsDTO) {
        return this.baseMapper.findList(ingredientsDTO);
    }

}
