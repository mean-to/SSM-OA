package com.ujiuy.auth.controller;

import com.ujiuy.auth.bean.Role;
import com.ujiuy.auth.bean.Sources;
import com.ujiuy.auth.service.AuthService;
import com.ujiuy.pro.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("auth")
public class Authcontroller {
    @Autowired
    AuthService as;
    @RequestMapping("getAll")
    @ResponseBody
    public Map<String, List> getAll(){
        Map<String, List> map = as.getAll();
        return map;
    }
@RequestMapping("addEmp")
    public  String addEmp(Employee emp,String roleid){
        int i=as.addEmp(emp,roleid);
        return "auth/user";

}
@RequestMapping("showAuth")
@ResponseBody
public List<Sources> showAuth(){
    List<Sources> slist = as.selectSources();
    return slist;
}
@RequestMapping("addSou")
public  String addSou(Model m,Sources s,int pid){
        int i=as.addSou(s,pid);
        return "auth/pm";
}
@RequestMapping("getOneById")
    public String getOneById(Model m,int id){
       Sources sou = as.getOneById(id);
       m.addAttribute("onesource",sou);
       return "auth/pm-edit";
}
@RequestMapping("updateInfo")
    public String updateInfo(Sources ss){
        int i =as.updateInfo(ss);
        return "auth/pm";
}
@RequestMapping("delete")
@ResponseBody
    public int  delete(int id){
        int i=as.delete(id);
        return i;

}
@RequestMapping("role")
    public  String queryRole(Model m){
        List<Role> rlist = as.queryRole();
      m.addAttribute("rlist",rlist);
return "auth/role";
}
@RequestMapping("addRole")
    public String addRole(Model m,Role r,String sourcesid){
    int i = as.saveR_S(r,sourcesid);
    return "redirect:/auth/role";
}
@RequestMapping("getRoleSourseId")
    public String getRoleSourseId(Model m,int rid){
     Role role = as.selectRole(rid);
    String sids=as.selectRS(rid);
    m.addAttribute("role",role);
    m.addAttribute("sids",sids);
    return "auth/role-edit";
}
@RequestMapping("updateR_S")
    public String updateR_S(Model m,Role r,String sids){
    int i = as.updateR_S(r,sids);
    return "redirect:/auth/role";
}


}
