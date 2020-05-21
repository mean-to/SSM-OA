package com.ujiuy.email.service;

import com.ujiuy.email.bean.Email;
import com.ujiuy.pro.bean.Employee;

import java.util.List;

public interface EmailService {
    List<Employee> getEmailEmps(int eid);

    void saveEmail(Email email);

    List<Email> queryEmail(Integer eid,int flag);
}
