package com.ujiuy.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.auth.bean.Role;
import com.ujiuy.auth.bean.Sources;
import com.ujiuy.auth.dao.EmpRoleMapper;
import com.ujiuy.auth.dao.RoleMapper;
import com.ujiuy.auth.dao.SourcesMapper;
import com.ujiuy.usual.bean.Task;
import com.ujiuy.usual.bean.TaskExample;
import com.ujiuy.usual.dao.TaskMapper;
import com.ujiuy.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaServiceImpl implements TaService {
    @Autowired
    TaskMapper tm;
    @Autowired
    EmpRoleMapper erm;
    @Autowired
    RoleMapper rm;
    @Autowired
    SourcesMapper sm;
    @Override
    public int saveTask(Task task) {
        int i = tm.insertSelective(task);
        return i;
    }

    @Override
    public PageInfo<Task> queryObj(Map map,int pageNO) {
        PageHelper.startPage(pageNO,2);
       List<Task> tlist = tm.queryObj(map);

        PageInfo<Task> info=new PageInfo<>(tlist);
        return info;
    }

    @Override
    public Task getTask(int id) {
        Task task = tm.selectByPrimaryKey(id);
        return task;
    }

    @Override
    public int updateTask(Task task) {
        int i = tm.updateByPrimaryKeySelective(task);
        return i;
    }

    @Override
    public PageInfo<Task> queryObjMy(int pageNO, QueryObj queryObj,int eid,int ta) {
        PageHelper.startPage(pageNO,2);
        Map map= new HashMap<String,Object>();
        map.put("qo",queryObj);
        map.put("eid",eid);
        map.put("ta",ta);
       List<Task> tlist = tm.queryObjMy(map);
       PageInfo info=new PageInfo(tlist);
        return info;
    }

    @Override
    public int queryNotTask(int eid) {
        TaskExample te=new TaskExample();
        TaskExample.Criteria cc = te.createCriteria();
        cc.andEmpFk2EqualTo(eid);
        cc.andStatusNotEqualTo(2);
        long count = tm.countByExample(te);
        return (int)count;
    }

    @Override
    public int startTask(int id, int st) {
        Task ta=new Task();
        ta.setId(id);
        ta.setStatus(st);
        int i = tm.updateByPrimaryKeySelective(ta);
        return i;
    }

    @Override
    public List<Role> getRoleId(int id) {
            List<Role> rlist= rm.getRoleId(id);
        return rlist;
    }

    @Override
    public List<Sources> getSourcesPId(Map map) {
        //根据map中的roleid，和pid得到权限内的列表，设置pid为1直接查询一级列表
        map.put("pid",1);
        List<Sources>  slist =sm.getSourcesPid(map);
        for (Sources sources : slist) {
            //获取的id为下一级别pid
            Integer id = sources.getId();
           map.put("pid",id);
            List<Sources> sslist = sm.getSourcesPid(map);
            sources.setChildren(sslist);
        }

        return slist;
    }


}
