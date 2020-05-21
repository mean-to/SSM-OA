package com.ujiuy.pro.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Analysis;
import com.ujiuy.pro.bean.Project;

import java.util.List;

public interface AnaService {
    public PageInfo<Analysis> findAnaList(int pageNO);

    List<Project> getProNoAna();

    int saveAna(Analysis ana);

    Analysis getByAna(int id);

    int updateAna(Analysis ana);

    int deleAna(List<Integer> ids);
}
