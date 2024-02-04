package com.ts.check.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ts.check.model.CheckItem;

import java.util.List;

public interface ICheckItemService extends IService<CheckItem> {

    boolean addChecItem(CheckItem checkItem);

    List<CheckItem> queryChecItem(CheckItem checkItem);

    boolean updateChecItem(CheckItem checkItem);

    CheckItem getChecItem(String id);

    boolean deleteChecItem(List<String> ids);
}
