package com.food.api.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.food.api.dao.TasteDao;
import com.food.api.dto.TasteDTO;
import com.food.api.service.TasteService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 口味管理业务层实现类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Service("tasteService")
public class TasteServiceImpl extends ServiceImpl<TasteDao, TasteDTO> implements TasteService {

    @Override
    public Page<TasteDTO> basePage(TasteDTO tasteDTO) {
        if (null != tasteDTO.getCurrent() && null != tasteDTO.getPageSize())
            tasteDTO.setCurrent((tasteDTO.getCurrent() - 1) * tasteDTO.getPageSize());
        List<TasteDTO> list = this.baseMapper.findList(tasteDTO);
        int count = this.baseMapper.findPageCount(tasteDTO);
        Page<TasteDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<TasteDTO> findList(TasteDTO tasteDTO) {
        return this.baseMapper.findList(tasteDTO);
    }

}
