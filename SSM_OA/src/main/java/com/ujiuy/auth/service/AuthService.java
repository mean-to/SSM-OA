package com.ujiuy.auth.service;

import com.ujiuy.auth.bean.Role;
import com.ujiuy.auth.bean.Sources;
import com.ujiuy.pro.bean.Employee;

import java.util.List;
import java.util.Map;

public interface AuthService {
    public Map<String, List> getAll();

    int addEmp(Employee emp, String roleid);

    List<Sources> selectSources();

    int addSou(Sources s,int pid);

    Sources getOneById(int id);

    int updateInfo(Sources ss);

    int delete(int id);

    List<Role> queryRole();

    int saveR_S(Role r,String sourcesid);

    Role selectRole(int rid);

    String selectRS(int rid);

    int updateR_S(Role r, String sids);
}
