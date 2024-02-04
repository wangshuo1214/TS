package com.ts.check.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ts.check.entity.HttpStatus;
import com.ts.check.entity.PageQuery;
import com.ts.check.entity.Result;
import com.ts.check.exception.BaseException;
import com.ts.check.model.DictData;
import com.ts.check.model.DictType;
import com.ts.check.service.IDictService;
import com.ts.check.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class DictController extends BaseController{

    @Autowired
    private IDictService iDictService;

    @PostMapping("/type/add")
    public Result addDictType(@RequestBody DictType dictType){
        return computeResult(iDictService.addDictType(dictType));
    }

    @PostMapping("/type/query")
    public Result queryDictType(@RequestBody PageQuery pageQuery){
        if (ObjectUtil.isEmpty(pageQuery) || ObjectUtil.isEmpty(pageQuery.getPage())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        startPage(pageQuery);
        DictType dictType = getPageItem(pageQuery,DictType.class);
        return success(formatTableData(iDictService.queryDictType(dictType)));
    }

    @GetMapping("/type/list")
    public Result dictTypeList(){
        return success(iDictService.queryDictType(new DictType()));
    }

    @GetMapping("/type/get")
    public Result getDictType(String id){
        return success(iDictService.getDictType(id));
    }

    @PostMapping("/type/update")
    public Result updateDictType(@RequestBody DictType dictType){
        return computeResult(iDictService.updateDictType(dictType));
    }

    @PostMapping("/type/delete")
    public Result deleteDictType(@RequestBody List<String> ids){
        return computeResult(iDictService.deleteDictType(ids));
    }

    @PostMapping("/data/add")
    public Result addDictData(@RequestBody DictData dictData){
        return computeResult(iDictService.addDictData(dictData));
    }

    @PostMapping("/data/update")
    public Result updateDictData(@RequestBody DictData dictData){
        return computeResult(iDictService.updateDictData(dictData));
    }

    @GetMapping("/data/get")
    public Result getDictData(String id){
        return success(iDictService.getDictData(id));
    }

    @PostMapping("/data/query")
    public Result queryDictData(@RequestBody PageQuery pageQuery){
        if (ObjectUtil.isEmpty(pageQuery) || ObjectUtil.isEmpty(pageQuery.getPage())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        startPage(pageQuery);
        DictData dictData = getPageItem(pageQuery,DictData.class);
        return success(formatTableData(iDictService.queryDictData(dictData)));
    }

    @PostMapping("/data/delete")
    public Result deleteDictData(@RequestBody List<String> ids){
        return computeResult(iDictService.deleteDictData(ids));
    }

    @GetMapping("/data/getDataByType")
    public Result getDictDataByType(String dictType){
        return success(iDictService.getDictDataByType(dictType));
    }

    @GetMapping("/data/sole")
    public Result getSoleDict(String dictType, String dictCode){
        return success(iDictService.getSoleDict(dictType,dictCode));
    }
}
