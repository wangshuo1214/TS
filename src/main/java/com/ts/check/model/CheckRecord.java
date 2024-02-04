package com.ts.check.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("CHECK_RECORD")
public class CheckRecord extends BaseModel{

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String deviceId;// 设备id

    private String checkUserId;// 巡检人id

    private Date checkTime;// 巡检时间

    private String validCheckItems;// 合格项
}
