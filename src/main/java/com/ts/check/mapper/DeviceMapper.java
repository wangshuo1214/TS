package com.ts.check.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.check.model.Device;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceMapper extends BaseMapper<Device> {
}
