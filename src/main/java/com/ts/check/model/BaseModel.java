package com.ts.check.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class BaseModel {


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date createTime;// 创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date updateTime;// 修改时间

    public String isDelete;//删除标志 0表示未删除 1表示已删除

    /** 请求参数 */
    @TableField(exist = false)
    private Map<String, Object> params;
}
