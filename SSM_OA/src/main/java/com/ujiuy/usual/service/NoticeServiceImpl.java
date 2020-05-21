package com.ujiuy.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.usual.bean.Notice;
import com.ujiuy.usual.dao.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
   @Autowired
    NoticeMapper nm;

    @Override
    public PageInfo<Notice> queryNotice(int pageNO) {
        PageHelper.startPage(pageNO,2);
        List<Notice> nlist =nm.selectByExample(null);
        PageInfo info=new PageInfo(nlist);
        return info;
    }

    @Override
    public List<Notice> getNotList() {

        return nm.getNotList();
    }

    @Override
    public Notice getTaskNid(int nid) {
        Notice notice = nm.selectByPrimaryKey(nid);
        return notice;
    }

}
