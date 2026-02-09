package com.food.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.food.api.dto.UserCollectDTO;
import java.util.List;
/**
* @description: 收藏&点赞业务层接口
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
public interface UserCollectService extends IService<UserCollectDTO> {

    /**
    * 默认分页方法
    * @param userCollectDTO
    * @return
    */
    Page<UserCollectDTO> basePage(UserCollectDTO userCollectDTO);


    /**
    * 根据XML中的sql语句进行查询
    * @param userCollectDTO
    * @return
    */
    List<UserCollectDTO> findList(UserCollectDTO userCollectDTO);

}
