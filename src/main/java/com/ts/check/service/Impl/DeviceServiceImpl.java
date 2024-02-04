package com.ts.check.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.check.entity.BaseConstant;
import com.ts.check.entity.HttpStatus;
import com.ts.check.exception.BaseException;
import com.ts.check.mapper.DeviceMapper;
import com.ts.check.model.Device;
import com.ts.check.service.IDeviceService;
import com.ts.check.utils.InitFieldUtil;
import com.ts.check.utils.MessageUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {
    @Override
    public boolean addDevice(Device device) {
        if (ObjectUtil.isEmpty(device) || StrUtil.hasEmpty(device.getName(),device.getType())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        // 校验设备名称是否重复
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Device::getName,device.getName());
        wrapper.lambda().eq(Device::getIsDelete, BaseConstant.FALSE);
        if (CollUtil.isNotEmpty(list(wrapper))){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.device.name.repeat"));
        }
        if (!InitFieldUtil.initField(device)){
            throw new BaseException(HttpStatus.ERROR,MessageUtil.getMessage("ts.initFieldError"));
        }
        return save(device);
    }

    @Override
    public List<Device> queryDevice(Device device) {
        QueryWrapper<Device> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(device.getType())){
            wrapper.lambda().eq(Device::getType,device.getType());
        }
        if (StrUtil.isNotEmpty(device.getName())){
            wrapper.lambda().like(Device::getName,device.getName());
        }
        wrapper.lambda().eq(Device::getIsDelete,BaseConstant.FALSE);
        return list(wrapper);
    }

    @Override
    public boolean updateDevice(Device device) {
        if (ObjectUtil.isEmpty(device) || StrUtil.hasEmpty(device.getUuid(),device.getName(),device.getType())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }

        //获取旧的设备对象
        Device old = getById(device.getUuid());
        if (!updateFlag(device,old)){
            old.setType(device.getType());
            old.setName(device.getName());
            old.setRemark(device.getRemark());
            old.setUpdateTime(new Date());
        }
        return updateById(old);
    }

    @Override
    public Device getDevice(String id) {
        if (StrUtil.isEmpty(id)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        Device device = getById(id);
        if (ObjectUtil.isEmpty(device) || StrUtil.equals(device.getIsDelete(),BaseConstant.TRUE)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.device.not.exist"));
        }
        return device;
    }

    @Override
    public boolean deleteDevice(List<String> ids) {
        if (CollUtil.isEmpty(ids)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        List<Device> devices = listByIds(ids);
        if (CollUtil.isEmpty(devices)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        Date updateTime = new Date();// 更新时间，保持统一
        devices.forEach(device -> {
            device.setIsDelete(BaseConstant.TRUE);
            device.setUpdateTime(updateTime);
        });
        return updateBatchById(devices);
    }

    private boolean updateFlag(Device newObj,Device oldObj){
        StringBuffer sb1 = new StringBuffer("");
        StringBuffer sb2 = new StringBuffer("");
        sb1.append(newObj.getType());
        sb2.append(oldObj.getType());
        sb1.append(newObj.getName());
        sb2.append(oldObj.getName());
        sb1.append(newObj.getRemark());
        sb2.append(oldObj.getRemark());
        return sb1.toString().equals(sb2.toString());
    }
}
