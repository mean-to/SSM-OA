package com.ujiuy.pro.service;

import com.ujiuy.pro.bean.Employee;

import java.util.List;

public interface EmpService {
    public List<Employee> getEmpsByPos(int pid);

    public Employee queryLogin(String username, String password);

    List<Employee> queryEmp();


    Employee queryEMp(Integer empFk);
}
