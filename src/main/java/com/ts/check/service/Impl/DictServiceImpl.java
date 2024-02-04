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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new BaseException(HttpStatus.ERROR, MessageUtil.getMessage("ts.initFieldError"));
        }

        return dictMapper.addDictType(dictType);
    }

    @Override
    public List<DictType> queryDictType(DictType dictType) {
        return dictMapper.queryDictType(dictType);
    }

    @Override
    public int updateDictType(DictType dictType) {
        if(ObjectUtil.isEmpty(dictType) || StrUtil.hasEmpty(dictType.getUuid(),dictType.getDictType(),dictType.getDictName())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        //校验字典类型名称是否重复
        List<DictType> repeats = dictMapper.queryDictByDictType(dictType.getDictType());
        if (CollUtil.isNotEmpty(repeats) &&
                CollUtil.isNotEmpty(repeats.stream().filter(o -> !o.getUuid().equals(dictType.getUuid())).collect(Collectors.toList()))
        ){
            throw new BaseException(HttpStatus.BAD_REQUEST,MessageUtil.getMessage("ts.dict.nameRepeat"));
        }
        DictType old = dictMapper.getDictType(dictType.getUuid());
        if(!dictTypeUpdateFlag(old,dictType)){
            old.setDictType(dictType.getDictType());
            old.setDictName(dictType.getDictName());
            old.setRemark(dictType.getRemark());
            old.setUpdateTime(new Date());
        }
        return dictMapper.updateDictType(old);
    }

    @Override
    public DictType getDictType(String id) {
        if (StrUtil.isEmpty(id)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        DictType dictType = dictMapper.getDictType(id);
        if (ObjectUtil.isEmpty(dictType)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.dict.not.exist"));
        }
        return dictType;
    }

    @Override
    @Transactional
    public int deleteDictType(List<String> ids) {
        if (CollUtil.isEmpty(ids)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        ids.forEach(id -> {
            dictMapper.deleteDictDataByType(id);
        });
        return dictMapper.deleteDictType(ids);
    }

    @Override
    public int addDictData(DictData dictData) {
        if (ObjectUtil.isEmpty(dictData) || StrUtil.hasEmpty(dictData.getDictTypeId(),dictData.getDictCode(),dictData.getDictName())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }

        if (CollUtil.isNotEmpty(dictMapper.checkDictDataUnique(dictData))){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.dict.nameRepeat"));
        }

        //初始化基本属性
        if (!InitFieldUtil.initField(dictData)){
            throw new BaseException(HttpStatus.ERROR, MessageUtil.getMessage("ts.initFieldError"));
        }

        return dictMapper.addDictData(dictData);
    }

    @Override
    public int updateDictData(DictData dictData) {
        if (ObjectUtil.isEmpty(dictData) || StrUtil.hasEmpty(dictData.getUuid(),dictData.getDictTypeId(),dictData.getDictCode(),dictData.getDictName())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        if (CollUtil.isNotEmpty(dictMapper.checkDictDataUnique(dictData))){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.dict.nameRepeat"));
        }
        DictData old = dictMapper.getDictData(dictData.getUuid());

        if (!dictDataUpdateFlag(old,dictData)){
            old.setDictCode(dictData.getDictCode());
            old.setDictName(dictData.getDictName());
            old.setRemark(dictData.getRemark());
            old.setUpdateTime(new Date());
        }

        return dictMapper.updateDictData(old);
    }

    @Override
    public DictData getDictData(String id) {
        if (StrUtil.isEmpty(id)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        DictData dictData = dictMapper.getDictData(id);
        if (ObjectUtil.isEmpty(dictData)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.dict.not.exist"));
        }
        return dictData;
    }

    @Override
    public List<DictData> queryDictData(DictData dictData) {
        return dictMapper.queryDictData(dictData);
    }

    @Override
    public int deleteDictData(List<String> ids) {
        if (CollUtil.isEmpty(ids)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        return dictMapper.deleteDictData(ids);
    }

    @Override
    public List<DictData> getDictDataByType(String dictType) {
        if (StrUtil.isEmpty(dictType)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        return dictMapper.getDictDataByType(dictType);
    }

    @Override
    public DictData getSoleDict(String dictType, String dictCode) {
        if (StrUtil.hasEmpty(dictType, dictCode)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        return dictMapper.getSoleDict(dictType,dictCode);
    }

    private boolean dictTypeUpdateFlag(DictType oldObj,DictType newObj){
        StringBuffer sb1 = new StringBuffer("");
        StringBuffer sb2 = new StringBuffer("");
        sb1.append(oldObj.getDictType());
        sb2.append(newObj.getDictType());
        sb1.append(oldObj.getDictName());
        sb2.append(newObj.getDictName());
        sb1.append(oldObj.getRemark());
        sb2.append(newObj.getRemark());

        return sb1.toString().equals(sb2.toString());
    }

    private boolean dictDataUpdateFlag(DictData oldObj,DictData newObj){
        StringBuffer sb1 = new StringBuffer("");
        StringBuffer sb2 = new StringBuffer("");
        sb1.append(oldObj.getDictCode());
        sb2.append(newObj.getDictCode());
        sb1.append(oldObj.getDictName());
        sb2.append(newObj.getDictName());
        sb1.append(oldObj.getRemark());
        sb2.append(newObj.getRemark());

        return sb1.toString().equals(sb2.toString());
    }
}
