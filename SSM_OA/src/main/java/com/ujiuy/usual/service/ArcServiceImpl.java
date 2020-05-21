package com.ujiuy.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.pro.dao.EmployeeMapper;
import com.ujiuy.usual.bean.Archives;
import com.ujiuy.usual.bean.ArchivesExample;
import com.ujiuy.usual.dao.ArchivesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Service
public class ArcServiceImpl implements ArcService {
    @Autowired
    ArchivesMapper am;
    @Autowired
    EmployeeMapper em;
    @Override
    public Employee arcMy(int eid) {
       Employee emp = em.arcMy(eid);
        return emp;
    }

    @Override
    public int saveOrupdate(Employee emp, HttpSession session) {
        Employee emp1 = (Employee) session.getAttribute("emp");
        int eid = emp1.getEid();
        emp.setEid(eid);
        int i = em.updateByPrimaryKeySelective(emp);
        String dnum = emp.getArc().getDnum();
        if(dnum!=null && dnum!=""){
            am.updateByPrimaryKeySelective(emp.getArc());
            return i;
        }else {
        Archives arc = emp.getArc();
        arc.setDnum(UUID.randomUUID().toString().substring(28));
        arc.setEmpFk(eid);
        am.insertSelective(arc);
        return i;
        }

    }

    @Override
    public PageInfo queryList(int pageNO) {
        PageHelper.startPage(pageNO,2);
        List<Employee> eList = em.queryList();
        PageInfo info=new PageInfo(eList);
        return info;
    }

    @Override
    public int saveMulti(List<Archives> alist) {
        int i= am.saveMulti(alist);

        return i;
    }

    @Override
    public List<Archives> getList() {
        List<Archives> alist = am.selectByExample(null);
        return alist;
    }

    @Override
    public Archives getArcByEmpId(Integer empFk2) {
        ArchivesExample ae=new ArchivesExample();
        ArchivesExample.Criteria cc = ae.createCriteria();
        cc.andEmpFkEqualTo(empFk2);
        List<Archives> alist = am.selectByExample(ae);

        return alist.get(0);
    }
}
