package com.ts.check.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("CHECK_RECORD")
public class CheckRecord extends BaseModel{

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String deviceId;// 设备id

    private String checkUserId;// 巡检人id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkTime;// 巡检时间

    private String validCheckItems;// 合格项

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date device_up_time;// 设备开机时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date device_down_time;// 设备停机时间

    private String check_description;;// 巡检描述

    @TableField(exist = false)
    private List<DeviceProblem> problems;
}
