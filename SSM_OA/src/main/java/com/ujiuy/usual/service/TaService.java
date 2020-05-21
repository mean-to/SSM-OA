package com.ujiuy.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.auth.bean.Role;
import com.ujiuy.auth.bean.Sources;
import com.ujiuy.usual.bean.Task;
import com.ujiuy.utils.QueryObj;

import java.util.List;
import java.util.Map;

public interface TaService {

    int saveTask(Task task);

    PageInfo<Task> queryObj(Map map,int pageNO);

    Task getTask(int id);

    int updateTask(Task task);

    PageInfo<Task> queryObjMy(int pageNO, QueryObj queryObj,int eid,int ta);

    int queryNotTask(int eid);


    int startTask(int id, int st);
    public List<Role> getRoleId(int id);

    List<Sources> getSourcesPId(Map map);
}
