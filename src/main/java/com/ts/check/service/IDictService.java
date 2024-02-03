package com.ts.check.service;

import com.ts.check.model.DictData;
import com.ts.check.model.DictType;

import java.util.List;

public interface IDictService {

    int addDictType(DictType dictType);

    List<DictType> queryDictType(DictType dictType);

    int updateDictType(DictType dictType);

    DictType getDictType(String id);

    int deleteDictType(List<String> ids);

    int addDictData(DictData dictData);

    int updateDictData(DictData dictData);

    DictData getDictData(String id);

    List<DictData> queryDictData(DictData dictData);

    int deleteDictData(List<String> ids);

    List<DictData> getDictDataByType(String dictType);

    DictData getSoleDict(String dictType, String dictCode);

}
