package com.food.api.common;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @description: dao数据访问层的基类
 * @author: wuming
 * @create: 2025-02-11 16:40:23
 */
public interface BaseDao<T> extends BaseMapper<T> {

    /**
     * 分页方法
     * @param entity
     * @return
     */
    List<T> findList(T entity);

    /**
     * 分页查数量方法
     * @param entity
     * @return
     */
    int findPageCount(T entity);

    /**
     * 根据实体查询单个对象的方法,慎用(可能因条件查出多个对象导致报错)
     * @param entity
     * @return
     */
    T find(T entity);

}
