package com.ts.check.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("DEVICE")
public class Device extends BaseModel{

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String name;// 设备名称

    private String type;// 设备类型

    private String remark;// 备注
}
