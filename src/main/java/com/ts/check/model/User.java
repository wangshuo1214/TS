package com.ts.check.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("USER")
public class User extends BaseModel{

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String name;// 用户名称

    private String loginName;// 登录名

    private String password;// 密码

    private String vxId;// 微信id
}
