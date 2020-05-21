package com.ujiuy.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.usual.bean.Baoxiao;

import java.util.List;

public interface BxService {
    PageInfo<Baoxiao> queryBxList(int pageNO,int flag);

    List<Baoxiao> mybxList(int eid);

    Baoxiao getBxId(String bx);

    int updateBx(Baoxiao baoxiao);

    int saveBx(Baoxiao bx);
}
