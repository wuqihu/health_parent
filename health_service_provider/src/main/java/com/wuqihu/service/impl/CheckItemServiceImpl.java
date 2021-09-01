package com.wuqihu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuqihu.dao.CheckItemDao;
import com.wuqihu.entity.PageResult;
import com.wuqihu.entity.QueryPageBean;
import com.wuqihu.pojo.CheckItem;
import com.wuqihu.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);
    }

    public void deleteById(Integer id) {
        Long count = checkItemDao.findCountByCheckItemId(id);
        if(count>0){
            throw new RuntimeException();
        }else {
            checkItemDao.deleteById(id);
        }
    }

    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    public CheckItem findById(Integer id) {
        CheckItem checkItem = checkItemDao.findById(id);
        return checkItem;
    }

    public List<CheckItem> findAll() {
        List<CheckItem> checkItems=checkItemDao.findAll();
        return checkItems;
    }
}
