package com.ujiuy.pro.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Attachment;
import com.ujiuy.pro.bean.AttachmentExample;
import com.ujiuy.pro.dao.AttachmentMapper;
import com.ujiuy.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class AttServiceImpl implements AttService {

    @Autowired
    AttachmentMapper am;

    @Override
    public PageInfo queryObj(int pageNO, QueryObj queryObj) {
        PageHelper.startPage(pageNO, 2);
        List<Attachment> alist = am.queryObj(queryObj);
        PageInfo info = new PageInfo(alist);
        return info;
    }

    @Override
    public int saveAtt(Attachment att) {
        int i = am.insertSelective(att);
        return i;
    }

    @Override
    public Attachment getIdFile(int id) {
        Attachment att = am.selectByPrimaryKey(id);
        return att;
    }

    @Override
    public int uptadeFile(Attachment att) {
        int i = am.updateByPrimaryKeySelective(att);
        return i;
    }

    @Override
    public int delMulti(List<Integer> ids) {
        AttachmentExample ae = new AttachmentExample();
        AttachmentExample.Criteria cc = ae.createCriteria();

        cc.andIdIn(ids);
        //从服务器删除
        List<Attachment> alist = am.selectByExample(ae);

        for (Attachment att : alist) {
            File file = new File("E:\\crmtext", att.getPath());
            if (file.exists()) {
                file.delete();
            }
        }
        int i = am.deleteByExample(ae);

        return i;
    }
}
