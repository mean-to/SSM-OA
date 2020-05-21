package com.ujiuy.pro.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Module;
import com.ujiuy.utils.QueryObj;

import java.util.List;

public interface MouService {
    public PageInfo<Module> queryObj(int pageNO, QueryObj queryObj);

    List<Module> getMouPro();

    int saeInfo(Module mou);

    Module queryMou(int id);

    int updateMou(Module mou);

    Module queryMouL(int id);

    List<Module> queryMouF(int id);
}
