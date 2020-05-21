package com.ujiuy.pro.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Analysis;
import com.ujiuy.pro.bean.Project;
import com.ujiuy.pro.bean.ProjectExample;
import com.ujiuy.pro.dao.AnalysisMapper;
import com.ujiuy.pro.dao.ProjectMapper;
import com.ujiuy.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProServiceImpl implements ProService {

    @Autowired
    ProjectMapper pm;

    @Autowired
    AnalysisMapper am;

    @Override
    public PageInfo<Project> queryObj(int pageNO, QueryObj queryObj) {
        PageHelper.startPage(pageNO, 2);
        List<Project> plist = pm.queryObj(queryObj);
        PageInfo<Project> info = new PageInfo<>(plist);
        return info;

    }

    @Override
    public int saveInfo(Project pro) {
        int i = pm.insertSelective(pro);
        return i;
    }

    @Override
    public Project getIdPos(int pid) {
        Project pro = pm.selectByPrimaryKey(pid);
        return pro;
    }

    @Override
    public Project getNoHasNeed() {
        return null;
    }

    @Override
    public int updatePro(Project pro) {
        int i = pm.updateByPrimaryKeySelective(pro);
        return i;
    }

    @Override
    public List<Analysis> getMouProHas() {
        List<Analysis> alist = am.selectByExample(null);
        return alist;
    }

    @Override
    public Project getByIdPos(int pid) {
        Project pro = pm.getByIdPos(pid);

        return pro;
    }

    @Override
    public int delbase(List<Integer> ids) {
        ProjectExample pe = new ProjectExample();
        ProjectExample.Criteria cc = pe.createCriteria();
        cc.andPidIn(ids);
        int i = pm.deleteByExample(pe);
        return i;
    }

    @Override
    public List<Project> queryAll() {
        List<Project> plist = pm.selectByExample(null);
        return plist;
    }


}



