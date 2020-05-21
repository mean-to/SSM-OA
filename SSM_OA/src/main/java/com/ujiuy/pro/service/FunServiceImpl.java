package com.ujiuy.pro.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.cus.bean.Customer;
import com.ujiuy.pro.bean.Function;
import com.ujiuy.pro.bean.FunctionExample;
import com.ujiuy.pro.dao.FunctionMapper;
import com.ujiuy.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunServiceImpl implements FunService {
    @Autowired
    FunctionMapper fm;

    @Override
    public PageInfo<Function> queryObj(int pageNO, QueryObj queryObj) {
        PageHelper.startPage(pageNO, 2);
        List<Function> flist = fm.queryObj(queryObj);

        PageInfo<Function> info = new PageInfo<>(flist);
        return info;
    }


    @Override
    public Function getIdFun(int id) {
        Function fun = fm.selectByPrimaryKey(id);
        return fun;
    }

    @Override
    public int addFun(Function fun) {
        int i = fm.insertSelective(fun);
        return i;
    }

    @Override
    public int updateFun(Function fun) {
        int i = fm.updateByPrimaryKeySelective(fun);
        return i;
    }

    @Override
    public int delFun(List<Integer> ids) {
        FunctionExample fe = new FunctionExample();
        FunctionExample.Criteria cc = fe.createCriteria();
        cc.andIdIn(ids);
        int i = fm.deleteByExample(fe);

        return i;
    }

    @Override
    public List<Function> getfunK(int id) {
        FunctionExample fe = new FunctionExample();
        FunctionExample.Criteria cc = fe.createCriteria();
        cc.andModeleFkEqualTo(id);
        List<Function> flist = fm.selectByExample(fe);
        return flist;
    }

    @Override
    public List<Function> getList() {
        List<Function> flist = fm.selectByExample(null);

        return flist;

    }
}
