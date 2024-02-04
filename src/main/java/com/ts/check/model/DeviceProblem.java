package com.ts.check.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("DEVICE_PROBLEM")
public class DeviceProblem extends BaseModel{

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String deviceId;// 设备名称

    private String validCheckItems;// 无效检查项id

    private Date time;// 巡检时间，与巡检记录表的巡检时间保持一致

    private String description;// 问题描述

    private String closeStatus;// 关闭状态，0表示未关闭，1表示已关闭，默认为未关闭

    private String closeUserId;// 关闭人id

    private Date closeTime;// 关闭时间

    private String closeDescription;// 关闭描述
}
