package com.ujiuy.db.service;

import com.ujiuy.db.bean.Datacollect;
import com.ujiuy.db.bean.Indexvalue;
import com.ujiuy.db.dao.DatacollectMapper;
import com.ujiuy.db.dao.IndexvalueMapper;
import com.ujiuy.utils.YTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbServiceImpl implements DbService {
    @Autowired
    DatacollectMapper dm;
    @Autowired
    IndexvalueMapper im;

    @Override
    public List<Datacollect> queryDbCom() {
       List<Datacollect> dlist = dm.queryDbcom();
        return dlist;
    }

    @Override
    public Datacollect getByIdDc(int id) {
        Datacollect datacollect = dm.selectByPrimaryKey(id);
        return datacollect;
    }

    @Override
    public int saveIv(Indexvalue iv) {
        int i = im.insertSelective(iv);
        return i;
    }

    @Override
    public List<Datacollect> selectDb(YTime ytime) {
        List<Datacollect> dlist = dm.selectDBTime(ytime);
        return dlist;
    }
}
