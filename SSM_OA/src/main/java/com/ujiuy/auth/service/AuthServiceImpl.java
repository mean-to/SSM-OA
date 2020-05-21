package com.ujiuy.auth.service;

import com.ujiuy.auth.bean.*;
import com.ujiuy.auth.dao.EmpRoleMapper;
import com.ujiuy.auth.dao.RoleMapper;
import com.ujiuy.auth.dao.RoleSourcesMapper;
import com.ujiuy.auth.dao.SourcesMapper;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.pro.dao.EmployeeMapper;
import com.ujiuy.usual.bean.Dept;
import com.ujiuy.usual.bean.Level;
import com.ujiuy.usual.bean.Position;
import com.ujiuy.usual.dao.DeptMapper;
import com.ujiuy.usual.dao.LevelMapper;
import com.ujiuy.usual.dao.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    DeptMapper dem;
    @Autowired
    LevelMapper lm;
    @Autowired
    PositionMapper pm;
    @Autowired
    RoleMapper rm;
    @Autowired
    EmployeeMapper em;
    @Autowired
    EmpRoleMapper erm;
    @Autowired
    SourcesMapper sm;
    @Autowired
    RoleSourcesMapper rsm;

    @Override
    public Map<String, List> getAll() {
        List<Dept> dlist = dem.selectByExample(null);
        List<Level> llist = lm.selectByExample(null);
        List<Position> plist = pm.selectByExample(null);
        List<Role> rlist = rm.selectByExample(null);
        Map map=new HashMap();
        map.put("dlist",dlist);
        map.put("llist",llist);
        map.put("rlist",rlist);
        map.put("plist",plist);
        return map;
    }

    @Override
    public int addEmp(Employee emp, String roleid) {
       em.insertSelective(emp);

        int i = insertEr(emp, roleid);
        return i;
    }
    public int insertEr(Employee emp,String roleid)
    {
        String[] rd = roleid.split(",");
        List<EmpRole> el=new ArrayList<>();
        for (String er : rd) {
            EmpRole empRole = new EmpRole();
            empRole.setEmpFk(emp.getEid());
            empRole.setRoleFk(Integer.parseInt(er));
            empRole.setErdis(emp.getEname()+"的角色");
            el.add(empRole);
        }
        int i = erm.insertAll(el);
        return i;
    }


    @Override
    public List<Sources> selectSources() {
        SourcesExample se=new SourcesExample();
        SourcesExample.Criteria cc = se.createCriteria();
        cc.andPidEqualTo(0);
        List<Sources> slist = sm.selectByExample(se);
        getChild(slist);
        return slist;
    }

    @Override
    public int addSou(Sources ss,int pid) {
        ss.setPid(pid);
        int i = sm.insertSelective(ss);
        return i;
    }

    @Override
    public Sources getOneById(int id) {
        Sources sources = sm.selectByPrimaryKey(id);
        return sources;
    }

    @Override
    public int updateInfo(Sources ss) {
        int i = sm.updateByPrimaryKeySelective(ss);
        return i;
    }

    @Override
    public int delete(int id) {
        int i = sm.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public List<Role> queryRole() {
        List<Role> roles = rm.selectByExample(null);

        return roles;
    }

    @Override
    public int saveR_S(Role r,String sourcesid) {
        int roleid = rm.insertSelective(r);
        int i = addRo_S(r, sourcesid);
        return i;
    }
    public int addRo_S(Role r,String sourcesid) {
        String[] split = sourcesid.split(",");
        List<RoleSources> rslist = new ArrayList<>();
        for (String s : split) {
            RoleSources rs = new RoleSources();
            rs.setRoleid(r.getRoleid());
            rs.setSid(Integer.parseInt(s));
            rs.setRsdis(r.getRolename() + "资源");
            rslist.add(rs);
        }
    return rsm.insertFor(rslist);
    }


    @Override
    public Role selectRole(int rid) {
        return   rm.selectByPrimaryKey(rid);
    }

    @Override
    public String selectRS(int rid) {
     String rs = rsm.selectSid(rid);
        return rs;
    }

    @Override
    public int updateR_S(Role r,String sids) {
        int i = rm.updateByPrimaryKeySelective(r);
        RoleSourcesExample rsem=new RoleSourcesExample();
        RoleSourcesExample.Criteria cc = rsem.createCriteria();
        cc.andRoleidEqualTo(r.getRoleid());
        int i1 = rsm.deleteByExample(rsem);
        int i2 = addRo_S(r,sids);
        return i2;
    }

    public void getChild(List<Sources> s){
        for (Sources ss : s) {
            SourcesExample se=new SourcesExample();
            SourcesExample.Criteria cc = se.createCriteria();
            cc.andPidEqualTo(ss.getId());
            List<Sources> slist = sm.selectByExample(se);
            if(slist!=null && slist.size()>0){
                ss.setChildren(slist);
                getChild(slist);
            }

        }
    }

}

