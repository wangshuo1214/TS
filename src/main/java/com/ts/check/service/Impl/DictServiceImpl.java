package com.ts.check.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ts.check.entity.HttpStatus;
import com.ts.check.exception.BaseException;
import com.ts.check.mapper.DictMapper;
import com.ts.check.model.DictData;
import com.ts.check.model.DictType;
import com.ts.check.service.IDictService;
import com.ts.check.utils.InitFieldUtil;
import com.ts.check.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictServiceImpl implements IDictService {

    @Autowired
    private DictMapper dictMapper;
    @Override
    public int addDictType(DictType dictType) {
        //参数校验
        if(ObjectUtil.isEmpty(dictType) || StrUtil.hasEmpty(dictType.getDictType(),dictType.getDictName())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        //校验字典类型名称是否重复
        if (CollUtil.isNotEmpty(dictMapper.queryDictByDictType(dictType.getDictType()))){
            throw new BaseException(HttpStatus.BAD_REQUEST,MessageUtil.getMessage("ts.dict.nameRepeat"));
        }
        //初始化基本属性
        if (!InitFieldUtil.initField(dictType)){
            throw new BaseException(HttpStatus.ERROR, MessageUtil.getMessage("bm.initFieldError"));
        }

        return dictMapper.addDictType(dictType);
    }

    @Override
    public List<DictType> queryDictType(DictType dictType) {
        return null;
    }

    @Override
    public int updateDictType(DictType dictType) {
        return 0;
    }

    @Override
    public DictType getDictType(String id) {
        return null;
    }

    @Override
    public int deleteDictType(List<String> ids) {
        return 0;
    }

    @Override
    public int addDictData(DictData dictData) {
        return 0;
    }

    @Override
    public int updateDictData(DictData dictData) {
        return 0;
    }

    @Override
    public DictData getDictData(String id) {
        return null;
    }

    @Override
    public List<DictData> queryDictData(DictData dictData) {
        return null;
    }

    @Override
    public int deleteDictData(List<String> ids) {
        return 0;
    }

    @Override
    public List<DictData> getDictDataByType(String dictType) {
        return null;
    }

    @Override
    public DictData getSoleDict(String dictType, String dictCode) {
        return null;
    }
}
