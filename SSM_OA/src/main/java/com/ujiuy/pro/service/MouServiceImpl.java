package com.ujiuy.pro.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Module;
import com.ujiuy.pro.bean.ModuleExample;
import com.ujiuy.pro.dao.ModuleMapper;
import com.ujiuy.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MouServiceImpl implements MouService {
    @Autowired
    ModuleMapper mm;

    @Override
    public PageInfo<Module> queryObj(int pageNO, QueryObj queryObj) {
        PageHelper.startPage(pageNO, 2);
        List<Module> mlist = mm.queryObj(queryObj);
        PageInfo<Module> info = new PageInfo<>(mlist);

        return info;
    }

    @Override
    public List<Module> getMouPro() {
        List<Module> mlist = mm.getMouPro();
        return mlist;
    }

    @Override
    public int saeInfo(Module mou) {
        int i = mm.insertSelective(mou);
        return i;
    }

    @Override
    public Module queryMou(int id) {
        Module mou = mm.selectByPrimaryKey(id);
        return mou;
    }

    @Override
    public int updateMou(Module mou) {
        int i = mm.updateByPrimaryKeySelective(mou);
        return i;
    }

    @Override
    public Module queryMouL(int id) {
        Module mou = mm.queryMouL(id);
        return mou;
    }

    @Override
    public List<Module> queryMouF(int id) {
        ModuleExample me = new ModuleExample();
        ModuleExample.Criteria cc = me.createCriteria();
        cc.andAnalysisFkEqualTo(id);
        List<Module> mlist = mm.selectByExample(me);
        return mlist;
    }
}
