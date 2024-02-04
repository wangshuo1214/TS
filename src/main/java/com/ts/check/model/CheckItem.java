package com.ts.check.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("CHECK_ITEM")
public class CheckItem extends BaseModel{

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String deviceType;// 设备类型

    private String checkType;// 巡检类型

    private String content;// 问题内容
}
