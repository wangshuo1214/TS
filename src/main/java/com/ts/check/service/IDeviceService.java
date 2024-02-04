package com.ts.check.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ts.check.model.Device;

import java.util.List;

public interface IDeviceService extends IService<Device> {

    boolean addDevice(Device device);

    List<Device> queryDevice(Device device);

    boolean updateDevice(Device device);

    Device getDevice(String id);

    boolean deleteDevice(List<String> ids);
}
