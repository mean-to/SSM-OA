package com.ujiuy.usual.controller;

import com.ujiuy.pro.bean.Employee;
import com.ujiuy.usual.bean.Evaluate;
import com.ujiuy.usual.bean.Forumpost;
import com.ujiuy.usual.service.FomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("fom")
public class FomController {
    @Autowired
    FomService fs;

    @RequestMapping
    @ResponseBody
    public List<Forumpost> queryList(Model m){
        List<Forumpost> flist = fs.queryFom();
        System.out.println(flist.size()+"+======================================================");
        return flist;
    }
    @RequestMapping("getMyFomId")
    public String getFomId(Model m,HttpSession session){
        Employee emp = (Employee) session.getAttribute("emp");
        int eid = emp.getEid();
        List<Forumpost> fmlist = fs.getFomIdList(eid);
        m.addAttribute("fmlist",fmlist);
        return "usual/forum-showmyself";

    }
    @RequestMapping("add")
    public String add(Model m,Forumpost fom,HttpSession session){
        Employee emp = (Employee) session.getAttribute("emp");
        int  eid = emp.getEid();
        fom.setEmpFk3(eid);
        fom.setCreatetime(new Date());
        fom.setStatus(0);
        int i = fs.saveFom(fom);
        if(i>0){
            return "redirect:/fom/getMyFomId";
        }else{
            return  "usual/forum-add";
        }


    }
    @RequestMapping("getContent")
    public String getContent(Model m,int fid){

        Forumpost fom = fs.getContent(fid);
        m.addAttribute("fom",fom);
        return "usual/forum-reply";
    }
    @RequestMapping("evadd")
    public  String  evAddP(Model m, Evaluate evaluate,HttpSession session){
        System.out.println(evaluate.getForumFk());
        Employee emp =(Employee) session.getAttribute("emp");
        Integer eid = emp.getEid();
        evaluate.setEmpFk4(eid);
        int i=fs.evAddP(evaluate);
        return "redirect:/fom/getContent?fid="+evaluate.getForumFk();

    }



}
