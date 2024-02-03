package com.ts.check.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("DICT_TYPE")
public class DictType extends BaseModel{

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String dictType;// 字典类型

    private String dictName;// 字典类型名称

    private String remark;// 备注
}
