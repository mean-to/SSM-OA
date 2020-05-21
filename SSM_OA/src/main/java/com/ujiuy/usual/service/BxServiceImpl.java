package com.ujiuy.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.usual.bean.Baoxiao;
import com.ujiuy.usual.bean.BaoxiaoExample;
import com.ujiuy.usual.dao.BaoxiaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BxServiceImpl implements BxService {
    @Autowired
    BaoxiaoMapper bm;
    @Override
    public PageInfo<Baoxiao> queryBxList(int pageNO,int flag) {
        PageHelper.startPage(pageNO,1);
        List<Baoxiao> blist = bm.queryBxList(flag);
        PageInfo<Baoxiao> info=new PageInfo<>(blist);
        return info;
    }

    @Override
    public List<Baoxiao> mybxList(int eid) {
        BaoxiaoExample be=new BaoxiaoExample();
        BaoxiaoExample.Criteria cc = be.createCriteria();
        cc.andEmpFkEqualTo(eid);
        List<Baoxiao> blist = bm.selectByExample(be);
        return blist;
    }

    @Override
    public Baoxiao getBxId(String id) {
        Baoxiao baoxiao = bm.selectByPrimaryKey(id);
        return baoxiao;
    }

    @Override
    public int updateBx(Baoxiao baoxiao) {
        int i = bm.updateByPrimaryKeySelective(baoxiao);
        return i;
    }

    @Override
    public int saveBx(Baoxiao bx) {
        int i = bm.insertSelective(bx);
        return i;
    }
}
