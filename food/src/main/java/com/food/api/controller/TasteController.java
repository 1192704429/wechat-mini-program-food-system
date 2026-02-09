package com.food.api.controller;

import com.food.api.common.BaseController;
import com.food.api.common.ReturnMsg;
import com.food.api.dto.TasteDTO;
import com.food.api.service.TasteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import com.food.api.common.BaseSelectEntity;
import cn.hutool.core.collection.CollectionUtil;
import java.util.Date;
/**
* @description: 口味管理对外接口层
* @author: wuming
* @create: 2025.02.11 16:40:23
*/
@RestController
@RequestMapping("/taste")
public class TasteController extends BaseController<TasteService, TasteDTO> {


    @Override
    @Resource(name = "tasteService")
    protected void setBaseService(TasteService tasteService) {
        this.baseService = tasteService;
    }

    /**
    * 默认分页查询
    * @param tasteDTO
    * @return
    */
    @PostMapping("/page")
    public ReturnMsg page(@RequestBody TasteDTO tasteDTO) {
        return ReturnMsg.ok(baseService.basePage(tasteDTO));
    }

    /**
    * 根据XML中的sql语句进行查询
    * @param tasteDTO
    * @return
    */
    @PostMapping(value = "/findList")
    public ReturnMsg findList(@RequestBody TasteDTO tasteDTO) {
        return ReturnMsg.ok(baseService.findList(tasteDTO));
    }

    @Override
    protected void beforeSave(TasteDTO entity) throws Exception {
        entity.setCreateTime(new Date());
        super.beforeSave(entity);
    }

}

