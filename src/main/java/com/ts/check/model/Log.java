package com.ts.check.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("LOG")
public class Log {

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String userId;// 操作人

    private Date time;// 操作时间

    private String content;// 操作内容
}
