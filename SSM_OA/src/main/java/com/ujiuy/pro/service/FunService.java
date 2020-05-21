package com.ujiuy.pro.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Function;
import com.ujiuy.utils.QueryObj;

import java.util.List;

public interface FunService {
    PageInfo<Function> queryObj(int pageNO, QueryObj queryObj);


    Function getIdFun(int id);

    int addFun(Function fun);

    int updateFun(Function fun);

    int delFun(List<Integer> ids);

    List<Function> getfunK(int id);

    List<Function> getList();
}
