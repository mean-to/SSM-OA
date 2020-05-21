package com.ujiuy.pro.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Analysis;
import com.ujiuy.pro.bean.Project;
import com.ujiuy.utils.QueryObj;

import java.util.List;

public interface ProService {
    public PageInfo<Project> queryObj(int pageNO, QueryObj queryObj);

    int saveInfo(Project pro);

    Project getIdPos(int pid);

    Project getNoHasNeed();

    int updatePro(Project pro);


    List<Analysis> getMouProHas();

    Project getByIdPos(int pid);

    int delbase(List<Integer> ids);

    List<Project> queryAll();
}
