package com.food.api.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.food.api.dao.UsersDao;
import com.food.api.dto.UsersDTO;
import com.food.api.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 用户管理业务层实现类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Service("usersService")
public class UsersServiceImpl extends ServiceImpl<UsersDao, UsersDTO> implements UsersService {

    @Override
    public Page<UsersDTO> basePage(UsersDTO usersDTO) {
        if (null != usersDTO.getCurrent() && null != usersDTO.getPageSize())
            usersDTO.setCurrent((usersDTO.getCurrent() - 1) * usersDTO.getPageSize());
        List<UsersDTO> list = this.baseMapper.findList(usersDTO);
        int count = this.baseMapper.findPageCount(usersDTO);
        Page<UsersDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<UsersDTO> findList(UsersDTO usersDTO) {
        return this.baseMapper.findList(usersDTO);
    }

}
