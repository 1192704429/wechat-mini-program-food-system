package com.food.api.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.food.api.dao.UserCollectDao;
import com.food.api.dto.UserCollectDTO;
import com.food.api.service.UserCollectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @description: 收藏&点赞业务层实现类
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@Service("userCollectService")
public class UserCollectServiceImpl extends ServiceImpl<UserCollectDao, UserCollectDTO> implements UserCollectService {

    @Override
    public Page<UserCollectDTO> basePage(UserCollectDTO userCollectDTO) {
        if (null != userCollectDTO.getCurrent() && null != userCollectDTO.getPageSize())
            userCollectDTO.setCurrent((userCollectDTO.getCurrent() - 1) * userCollectDTO.getPageSize());
        List<UserCollectDTO> list = this.baseMapper.findList(userCollectDTO);
        int count = this.baseMapper.findPageCount(userCollectDTO);
        Page<UserCollectDTO> page = new Page<>();
        page.setTotal(count);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<UserCollectDTO> findList(UserCollectDTO userCollectDTO) {
        return this.baseMapper.findList(userCollectDTO);
    }

}
