package com.ts.check.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ts.check.entity.HttpStatus;
import com.ts.check.entity.PageQuery;
import com.ts.check.entity.Result;
import com.ts.check.exception.BaseException;
import com.ts.check.model.Device;
import com.ts.check.service.IDeviceService;
import com.ts.check.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController extends BaseController{

    @Autowired
    private IDeviceService iDeviceService;

    @PostMapping("/add")
    public Result addDevice(@RequestBody Device device){
        return computeResult(iDeviceService.addDevice(device));
    }

    @PostMapping("/query")
    public Result queryDevice(@RequestBody PageQuery pageQuery){
        if (ObjectUtil.isEmpty(pageQuery) || ObjectUtil.isEmpty(pageQuery.getPage())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        startPage(pageQuery);
        Device device = getPageItem(pageQuery, Device.class);
        return success(formatTableData(iDeviceService.queryDevice(device)));
    }

    @PostMapping("/update")
    public Result updateDevice(@RequestBody Device device){
        return computeResult(iDeviceService.updateDevice(device));
    }

    @GetMapping("/get")
    public Result getDevice(String id){
        return success(iDeviceService.getDevice(id));
    }

    @PostMapping("/delete")
    public Result deleteDevice(@RequestBody List<String> ids){
        return computeResult(iDeviceService.deleteDevice(ids));
    }
}
