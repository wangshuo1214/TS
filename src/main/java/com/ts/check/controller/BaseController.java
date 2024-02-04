package com.ts.check.controller;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.ts.check.entity.*;
import com.ts.check.exception.BaseException;
import com.ts.check.utils.CreateGsonUtil;
import com.ts.check.utils.MessageUtil;

import java.util.List;

public class BaseController {

    public Result success(){
        return Result.returnCodeMessage(HttpStatus.SUCCESS, MessageUtil.getMessage("ts.sucess"));
    }

    public Result success(Object data) {
        return Result.returnCodeMessage(HttpStatus.SUCCESS,MessageUtil.getMessage("ts.sucess"),data);
    }

    public Result error() {
        return Result.returnCodeMessage(HttpStatus.ERROR,MessageUtil.getMessage("ts.false"));
    }

    public Result error(String msg){
        return Result.returnCodeMessage(HttpStatus.ERROR,msg,null);
    }

    public Result computeResult(Object t){
        if ( t instanceof Boolean){
            return (boolean)t ? success() : error();
        }else if (t instanceof Integer){
            return (int) t > 0 ? success() : error();
        }
        return error(MessageUtil.getMessage("ts.computedResultError"));
    }

    public Result diyResut(Integer code, String msg){
        return Result.returnCodeMessage(code,msg);
    }

    public Result diyResut(Integer code, String msg, Object data){
        return Result.returnCodeMessage(code,msg,data);
    }

    protected void startPage(PageQuery pageQuery){
        if (ObjectUtil.isEmpty(pageQuery)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        PageDomain page = pageQuery.getPage();
        if (ObjectUtil.hasEmpty(page.getPageNum(), page.getPageSize())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        Integer pageNum = page.getPageNum();
        Integer pageSize = page.getPageSize();
        String orderBy = page.getOrderBy();
        PageHelper.startPage(pageNum, pageSize, orderBy);
    }

    protected TableDataInfo formatTableData(List<?> formatList){
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(formatList);
        rspData.setTotal(new PageInfo(formatList).getTotal());
        return rspData;
    }

    protected <T> T getPageItem(PageQuery pageQuery,Class<T> clazz){
        Gson gson = CreateGsonUtil.createGson();
        return gson.fromJson(gson.toJson(pageQuery.getItem()), clazz);
    }
}
