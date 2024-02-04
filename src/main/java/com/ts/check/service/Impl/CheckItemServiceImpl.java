package com.ts.check.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ts.check.entity.BaseConstant;
import com.ts.check.entity.HttpStatus;
import com.ts.check.exception.BaseException;
import com.ts.check.mapper.CheckItemMapper;
import com.ts.check.model.CheckItem;
import com.ts.check.service.ICheckItemService;
import com.ts.check.utils.InitFieldUtil;
import com.ts.check.utils.MessageUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CheckItemServiceImpl extends ServiceImpl<CheckItemMapper, CheckItem> implements ICheckItemService {
    @Override
    public boolean addChecItem(CheckItem checkItem) {
        if (ObjectUtil.isEmpty(checkItem)
                || StrUtil.hasEmpty(checkItem.getDeviceType(), checkItem.getCheckType(), checkItem.getContent())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        // 问题清单重复性校验
        QueryWrapper<CheckItem> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(CheckItem::getDeviceType, checkItem.getDeviceType());
        wrapper.lambda().eq(CheckItem::getCheckType, checkItem.getCheckType());
        wrapper.lambda().eq(CheckItem::getContent, checkItem.getContent());
        wrapper.lambda().eq(CheckItem::getIsDelete, BaseConstant.FALSE);
        if (CollUtil.isNotEmpty(list(wrapper))){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.checkitem.name.repeat"));
        }
        if (!InitFieldUtil.initField(checkItem)){
            throw new BaseException(HttpStatus.ERROR,MessageUtil.getMessage("ts.initFieldError"));
        }
        return save(checkItem);
    }

    @Override
    public List<CheckItem> queryChecItem(CheckItem checkItem) {
        QueryWrapper<CheckItem> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(checkItem.getDeviceType())){
            wrapper.lambda().eq(CheckItem::getDeviceType,checkItem.getDeviceType());
        }
        if (StrUtil.isNotEmpty(checkItem.getCheckType())){
            wrapper.lambda().eq(CheckItem::getCheckType,checkItem.getCheckType());
        }
        if (StrUtil.isNotEmpty(checkItem.getContent())){
            wrapper.lambda().like(CheckItem::getContent,checkItem.getContent());
        }
        wrapper.lambda().eq(CheckItem::getIsDelete,BaseConstant.FALSE);
        return list(wrapper);
    }

    @Override
    public boolean updateChecItem(CheckItem checkItem) {
        if (ObjectUtil.isEmpty(checkItem) ||
                StrUtil.hasEmpty(checkItem.getUuid(),checkItem.getDeviceType(),checkItem.getCheckType())){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        CheckItem old = getById(checkItem.getUuid());
        if (!updateFlag(checkItem,old)){
            old.setDeviceType(checkItem.getDeviceType());
            old.setCheckType(checkItem.getCheckType());
            old.setContent(checkItem.getContent());
            old.setUpdateTime(new Date());
        }
        return updateById(old);
    }

    @Override
    public CheckItem getChecItem(String id) {
        if (StrUtil.isEmpty(id)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        CheckItem checkItem = getById(id);
        if (ObjectUtil.isEmpty(checkItem) || StrUtil.equals(checkItem.getIsDelete(),BaseConstant.TRUE)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.checkitem.not.exist"));
        }
        return checkItem;
    }

    @Override
    public boolean deleteChecItem(List<String> ids) {
        if (CollUtil.isEmpty(ids)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        List<CheckItem> checkItems = listByIds(ids);
        if (CollUtil.isEmpty(checkItems)){
            throw new BaseException(HttpStatus.BAD_REQUEST, MessageUtil.getMessage("ts.paramsError"));
        }
        Date updateTime = new Date();// 更新时间，保持统一
        checkItems.forEach(checkItem -> {
            checkItem.setIsDelete(BaseConstant.TRUE);
            checkItem.setUpdateTime(updateTime);
        });
        return updateBatchById(checkItems);
    }

    private boolean updateFlag(CheckItem newObj, CheckItem oldObj){
        StringBuffer sb1 = new StringBuffer("");
        StringBuffer sb2 = new StringBuffer("");
        sb1.append(newObj.getDeviceType());
        sb2.append(oldObj.getDeviceType());
        sb1.append(newObj.getCheckType());
        sb2.append(oldObj.getCheckType());
        sb1.append(newObj.getContent());
        sb2.append(oldObj.getContent());
        return sb1.toString().equals(sb2.toString());
    }
}
