package com.ts.check.controller;


import cn.hutool.core.util.ObjectUtil;
import com.ts.check.entity.HttpStatus;
import com.ts.check.entity.PageQuery;
import com.ts.check.entity.Result;
import com.ts.check.exception.BaseException;
import com.ts.check.model.CheckItem;
import com.ts.check.service.ICheckItemService;
import com.ts.check.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController extends BaseController{

    @Autowired
    private ICheckItemService iCheckItemService;

    @PostMapping("/add")
    public Result addChecItem(@RequestBody CheckItem checkItem){
        return computeResult(iCheckItemService.addChecItem(checkItem));
    }

    @PostMapping("/query")
    public Result queryCheckItem(@RequestBody PageQuery pageQuery){
        if (ObjectUtil.isEmpty(pageQuery) || ObjectUtil.isEmpty(pageQuery.getPage())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        startPage(pageQuery);
        CheckItem checkItem = getPageItem(pageQuery, CheckItem.class);
        return success(formatTableData(iCheckItemService.queryChecItem(checkItem)));
    }

    @PostMapping("/update")
    public Result updateCheckItem(@RequestBody CheckItem checkItem){
        return computeResult(iCheckItemService.updateChecItem(checkItem));
    }

    @GetMapping("/get")
    public Result getCheckItem(String id){
        return success(iCheckItemService.getChecItem(id));
    }

    @PostMapping("/delete")
    public Result deleteCheckItem(@RequestBody List<String> ids){
        return computeResult(iCheckItemService.deleteChecItem(ids));
    }
}
