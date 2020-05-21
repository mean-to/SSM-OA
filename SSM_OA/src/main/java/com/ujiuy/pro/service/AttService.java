package com.ujiuy.pro.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Attachment;
import com.ujiuy.utils.QueryObj;

import java.util.List;

public interface AttService {
    PageInfo queryObj(int pageNO, QueryObj queryObj);

    int saveAtt(Attachment att);

    Attachment getIdFile(int id);

    int uptadeFile(Attachment att);

    int delMulti(List<Integer> ids);
}
