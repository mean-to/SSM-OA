package com.ujiuy.pro.service;

import com.ujiuy.pro.bean.Employee;
import com.ujiuy.pro.bean.EmployeeExample;
import com.ujiuy.pro.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmployeeMapper em;

    @Override
    public List<Employee> getEmpsByPos(int pid) {
        EmployeeExample ee = new EmployeeExample();
        EmployeeExample.Criteria cc = ee.createCriteria();
        cc.andPFkEqualTo(pid);
        return em.selectByExample(ee);
    }

    @Override
    public Employee queryLogin(String username, String password) {
        EmployeeExample ee = new EmployeeExample();
        EmployeeExample.Criteria cc = ee.createCriteria();
        cc.andUsernameEqualTo(username);
        cc.andPasswordEqualTo(password);
        List<Employee> emlist = em.selectByExample(ee);
        if (emlist != null && emlist.size() > 0) {
            return emlist.get(0);
        }
        return null;
    }

    @Override
    public List<Employee> queryEmp() {
        EmployeeExample ee = new EmployeeExample();
        EmployeeExample.Criteria cc = ee.createCriteria();
        cc.andPFkLessThan(4);
        List<Employee> elist = em.selectByExample(ee);

        return elist;
    }

    @Override
    public Employee queryEMp(Integer empFk) {
        Employee employee = em.selectByPrimaryKey(empFk);
        return employee;
    }


}
