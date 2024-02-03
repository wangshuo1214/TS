package com.ts.check.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("DICT_DATA")
public class DictData extends BaseModel{

    @TableId(type = IdType.ASSIGN_UUID)
    private String uuid;

    private String dictCode;// 字典编码

    private String dictName;// 字典名称

    private String dictTypeId;// 字典类型id

    private String remark;// 备注
}
