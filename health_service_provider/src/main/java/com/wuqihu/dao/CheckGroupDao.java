package com.wuqihu.dao;

import com.github.pagehelper.Page;
import com.wuqihu.pojo.CheckGroup;
import com.wuqihu.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    Page<CheckItem> selectByCondition(String queryString);

    CheckGroup findById(Integer checkGroupId);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer checkitemId);

    void edit(CheckGroup checkGroup);

    void deleteByCheckGroupId(Integer checkGroupId);
}
