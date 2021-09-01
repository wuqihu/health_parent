package com.wuqihu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuqihu.dao.CheckGroupDao;
import com.wuqihu.entity.PageResult;
import com.wuqihu.entity.QueryPageBean;
import com.wuqihu.pojo.CheckGroup;
import com.wuqihu.pojo.CheckItem;
import com.wuqihu.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    //检擦组dao
    @Autowired
    private CheckGroupDao checkGroupDao;
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //新增检查组
        checkGroupDao.add(checkGroup);
        //获得新增检查组id
        setCheckGroupAndCheckItem(checkGroup,checkitemIds);
    }

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkGroupDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);
    }

    public CheckGroup findById(Integer checkGroupId) {
        CheckGroup checkGroup = checkGroupDao.findById(checkGroupId);
        return checkGroup;
    }

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer checkitemId) {
        List<Integer> checkItemIds = checkGroupDao.findCheckItemIdsByCheckGroupId(checkitemId);
        return checkItemIds;
    }

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.edit(checkGroup);
        //删除关系
        checkGroupDao.deleteByCheckGroupId(checkGroup.getId());
        //添加关系表
        setCheckGroupAndCheckItem(checkGroup,checkitemIds);
    }
    public void setCheckGroupAndCheckItem(CheckGroup checkGroup,Integer[] checkitemIds){
        Integer checkGroupId = checkGroup.getId();
        if(checkitemIds != null && checkitemIds.length>0){
            for (Integer checkitemId:checkitemIds) {
                Map<String,Integer> map = new HashMap<String, Integer>();
                map.put("checkgroupId",checkGroupId);
                map.put("checkitemId",checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }
}
