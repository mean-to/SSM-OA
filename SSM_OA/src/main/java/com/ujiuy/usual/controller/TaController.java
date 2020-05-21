package com.ujiuy.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.auth.bean.Role;
import com.ujiuy.auth.bean.Sources;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.pro.bean.Function;
import com.ujiuy.pro.bean.Module;
import com.ujiuy.pro.service.EmpService;
import com.ujiuy.pro.service.FunService;
import com.ujiuy.pro.service.MouService;
import com.ujiuy.usual.bean.Task;
import com.ujiuy.usual.service.TaService;
import com.ujiuy.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("task")
public class TaController {
@Autowired
    TaService ts;
    @Autowired
    EmpService es;

    @Autowired
    FunService fs;

    @Autowired
    MouService ms;
    //登录
    @RequestMapping("login")
    public String login(Model m, String username, String password, HttpSession session){
        Employee emp = es.queryLogin(username, password);
        if(emp!=null){
            session.setAttribute("emp",emp);
            //根据登录人的id查询角色,查询出角色
            List<Role> rlist = ts.getRoleId(emp.getEid());

            //查出第一个角色的权限列表
            if(rlist!=null && rlist.size()>0){
                Integer roleid = rlist.get(0).getRoleid();
                //创建map吧roleid存入map，查出他所拥有的权限
                Map map=new HashMap();
                map.put("roleid",roleid);
                //调用serive，传入map
                List<Sources> slist=ts.getSourcesPId(map);
                session.setAttribute("mlist",slist);
                session.setAttribute("rilist",rlist);
                session.setAttribute("rid",roleid);
            }
            return "main/index";
        }else {
            return "main/login";
        }
    }




    @RequestMapping
    public String queryObj( Model m, QueryObj queryObj, HttpSession session, @RequestParam(defaultValue = "-1") int st, @RequestParam(defaultValue = "1") int pageNO){
        Map map=new HashMap();
        map.put("qo",queryObj);
        map.put("st",st);
        Employee emp = (Employee) session.getAttribute("emp");
        map.put("eid",emp.getEid());
        PageInfo<Task> info = ts.queryObj(map,pageNO);
        m.addAttribute("qo",queryObj);
        m.addAttribute("info",info);
        return "usual/task";
    }


    @RequestMapping("getIdEmp")
    @ResponseBody
    public List<Employee> getIdEmp(Model m) {
        List<Employee> elist=es.queryEmp();

        return elist;
    }

    @RequestMapping("addInfo")
    public String addInfo(Model m, Task task,HttpSession session){
        Employee emp= (Employee) session.getAttribute("emp");
        task.setEmpFk(emp.getEid());
        task.setStatus(0);
        int i=ts.saveTask(task);
        return "usual/task-add";
    }
    @RequestMapping("getTask")
    public String getIdTask(Model model,int id){
        Task task=ts.getTask(id);
        model.addAttribute("task",task);
        Function fun=fs.getIdFun(task.getFunFk());
        Module mod = ms.queryMou(fun.getModeleFk());
        model.addAttribute("m",mod);
        model.addAttribute("fun",fun);
        return "usual/task-edit";
    }
    @RequestMapping("updatetask")
    public  String updatetask(Task task, HttpSession session){
        Employee emp = (Employee)session.getAttribute("emp");
        int  eid = emp.getEid();
        task.setEmpFk(eid);
       int i= ts.updateTask(task);


        return "redirect:/task";
    }

    @RequestMapping("taskmy")
    public String taskMy(@RequestParam(defaultValue = "3") int ta,Model m,@RequestParam(defaultValue = "1") int pageNO,QueryObj queryObj,HttpSession session){


        Employee emp = (Employee)session.getAttribute("emp");
       int eid = emp.getEid();
        PageInfo<Task> info = ts.queryObjMy(pageNO,queryObj,eid,ta);
        m.addAttribute("info",info);
        return "usual/task-my";
    }
@RequestMapping("getNotTask")
@ResponseBody
    public int  getNotTask(HttpSession session){

    Employee emp = (Employee) session.getAttribute("emp");
    int eid = emp.getEid();
    int count = ts.queryNotTask(eid);
    System.out.println(count);
    return count;
}

    @RequestMapping("startTask")
    public String startTask(int id,int st){
        int i=ts.startTask(id,st);
        System.out.println(id+"-------"+st);
        return "redirect:/task/taskmy";
    }




}
