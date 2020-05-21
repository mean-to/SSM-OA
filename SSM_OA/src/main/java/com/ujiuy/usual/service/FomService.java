package com.ujiuy.usual.service;

import com.ujiuy.usual.bean.Evaluate;
import com.ujiuy.usual.bean.Forumpost;

import java.util.List;

public interface FomService {
    List<Forumpost> queryFom();

    List<Forumpost> getFomIdList(int eid);

    int saveFom(Forumpost fom);

    Forumpost getContent(int fid);


    int evAddP(Evaluate evaluate);
}
