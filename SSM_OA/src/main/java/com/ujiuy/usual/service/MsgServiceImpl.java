package com.ujiuy.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.pro.bean.EmployeeExample;
import com.ujiuy.pro.dao.EmployeeMapper;
import com.ujiuy.usual.bean.Msg;
import com.ujiuy.usual.dao.MsgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MsgServiceImpl implements MsgService {
    @Autowired
    MsgMapper mm;
    @Autowired
    EmployeeMapper em;
    @Override
    public PageInfo<Msg> queryMsgList(int pageNO) {

        PageHelper.startPage(pageNO,1);
         List<Msg> mlist =mm.queryMsgList();
        System.out.println(mlist.get(0));
        PageInfo<Msg> info = new PageInfo<>(mlist);

        return info;
    }

    @Override
    public List<Employee> getEmpNotId(int eid) {
        EmployeeExample ee=new EmployeeExample();
        EmployeeExample.Criteria cc = ee.createCriteria();
        cc.andEidNotEqualTo(eid);
        List<Employee> elist = em.selectByExample(ee);
        return elist;
    }
}
