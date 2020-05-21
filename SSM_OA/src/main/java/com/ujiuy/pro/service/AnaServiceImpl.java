package com.ujiuy.pro.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Analysis;
import com.ujiuy.pro.bean.AnalysisExample;
import com.ujiuy.pro.bean.Project;
import com.ujiuy.pro.dao.AnalysisMapper;
import com.ujiuy.pro.dao.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaServiceImpl implements AnaService {
    @Autowired
    AnalysisMapper am;
    @Autowired
    ProjectMapper pm;

    @Override
    public PageInfo<Analysis> findAnaList(int pageNO) {
        PageHelper.startPage(pageNO, 1);

        List<Analysis> alist = am.selectByExample(null);
        PageInfo<Analysis> info = new PageInfo<>(alist);
        return info;

    }

    @Override
    public List<Project> getProNoAna() {
        List<Project> plist = pm.getProNoAna();
        return plist;
    }

    @Override
    public int saveAna(Analysis ana) {
        int i = am.insertSelective(ana);
        return i;
    }

    @Override
    public Analysis getByAna(int id) {
        Analysis ana = am.selectByPrimaryKey(id);

        return ana;
    }

    @Override
    public int updateAna(Analysis ana) {
        int i = am.updateByPrimaryKeySelective(ana);
        return i;
    }

    @Override
    public int deleAna(List<Integer> ids) {
        AnalysisExample ae = new AnalysisExample();
        AnalysisExample.Criteria cc = ae.createCriteria();
        cc.andIdIn(ids);
        int i = am.deleteByExample(ae);
        return i;
    }
}
