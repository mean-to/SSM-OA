package com.ujiuy.db.service;

import com.ujiuy.db.bean.Datacollect;
import com.ujiuy.db.bean.Indexvalue;
import com.ujiuy.utils.YTime;

import java.util.List;

public interface DbService {
    List<Datacollect> queryDbCom();

    Datacollect getByIdDc(int id);

    int saveIv(Indexvalue iv);

    List<Datacollect> selectDb(YTime ytime);
}
