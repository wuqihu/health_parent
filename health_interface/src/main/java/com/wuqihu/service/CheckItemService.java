package com.wuqihu.service;

import com.wuqihu.entity.PageResult;
import com.wuqihu.entity.QueryPageBean;
import com.wuqihu.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    void add(CheckItem checkItem);

    PageResult findPage(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();
}
