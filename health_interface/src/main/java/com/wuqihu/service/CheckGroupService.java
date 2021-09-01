package com.wuqihu.service;

import com.wuqihu.entity.PageResult;
import com.wuqihu.entity.QueryPageBean;
import com.wuqihu.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    void add(CheckGroup checkGroup,Integer[] checkitemIds);

    PageResult findPage(QueryPageBean queryPageBean);

    CheckGroup findById(Integer checkGroupId);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer checkitemId);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);
}
