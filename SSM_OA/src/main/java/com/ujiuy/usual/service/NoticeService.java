package com.ujiuy.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.usual.bean.Notice;

import java.util.List;

public interface NoticeService {
    PageInfo<Notice> queryNotice(int pageNO);
    List<Notice> getNotList();

    Notice getTaskNid(int nid);
}
