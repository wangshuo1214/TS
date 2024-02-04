package com.ts.check.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ts.check.model.CheckItem;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CheckItemMapper extends BaseMapper<CheckItem> {
}
