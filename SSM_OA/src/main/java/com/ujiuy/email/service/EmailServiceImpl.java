package com.ujiuy.email.service;

import com.ujiuy.email.bean.Email;
import com.ujiuy.email.bean.EmailExample;
import com.ujiuy.email.dao.EmailMapper;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.pro.bean.EmployeeExample;
import com.ujiuy.pro.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    EmailMapper emailM;
    @Autowired
    EmployeeMapper em;

    @Override
    public List<Employee> getEmailEmps(int eid) {
        EmployeeExample ee=new EmployeeExample();
        EmployeeExample.Criteria cc = ee.createCriteria();
        cc.andEidNotEqualTo(eid);
        List<Employee> elist = em.selectByExample(ee);
        return elist;
    }

    @Override
    public void saveEmail(Email email) {
      emailM.insertSelective(email);
    }



    @Override
    public List<Email> queryEmail(Integer eid,int flag) {

        if(flag==1){
            EmailExample ee=new EmailExample();
            EmailExample.Criteria cc = ee.createCriteria();
            cc.andEmpFkEqualTo(eid);
            List<Email> emails = emailM.selectByExample(ee);
            for (Email email : emails) {
                Employee employee = em.selectByPrimaryKey(email.getEmpFk2());
            email.setEmp(employee);
            }
            return emails;
        }
        if(flag==0){
            EmailExample ee=new EmailExample();
            EmailExample.Criteria cc = ee.createCriteria();
            cc.andEmpFk2EqualTo(eid);
            List<Email> emails = emailM.selectByExample(ee);

            for (Email email : emails) {
                Employee employee = em.selectByPrimaryKey(email.getEmpFk());
                email.setEmp(employee);
            }
            return emails;
        }
       return null;
    }
}
