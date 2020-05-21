package com.ujiuy.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.usual.bean.Baoxiao;
import com.ujiuy.usual.service.BxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("bx")
public class BxController {
    @Autowired
    BxService bs;
    @RequestMapping
    public String queryBx(Model m,@RequestParam(defaultValue = "1") int flag, @RequestParam(defaultValue = "1") int pageNO){

           PageInfo<Baoxiao> info = bs.queryBxList(pageNO,flag);
           m.addAttribute("info",info);
           m.addAttribute("flag",flag);
        return "usual/baoxiao-task";
    }
    @RequestMapping("mybx")
    public String mybxList(Model model, HttpSession session){
        Employee em = (Employee) session.getAttribute("emp");
        int eid = em.getEid();
       List<Baoxiao> blist = bs.mybxList(eid);
       model.addAttribute("blist",blist);
       return "usual/mybaoxiao-base";

    }
    @RequestMapping("getBxId")
    public String getBxId(Model m,@RequestParam(defaultValue = "1") int fl, String id){
       Baoxiao bxiao = bs.getBxId(id);
       m.addAttribute("bs",bxiao);
       if(fl==1){
           return "usual/baoxiao-task-edit";
       }else {
           return "usual/mybaoxiao-edit";
       }

    }
    @RequestMapping("updateBx")
    public String updateBx(Model m,@RequestParam(defaultValue = "1")int ft, Baoxiao baoxiao){
        int i=bs.updateBx(baoxiao);
if(ft==1){
    return "redirect:/bx";
}else {
    return "redirect:/bx/mybx";
}

    }
    @RequestMapping("saveInfo")
    public String addBx(Model m,Baoxiao bx,HttpSession session){
        Employee em = (Employee) session.getAttribute("emp");
        int eid = em.getEid();
        bx.setEmpFk(eid);
        bx.setBxid(UUID.randomUUID().toString().substring(1));
        int i= bs.saveBx(bx);
        return "redirect:/bx/mybx";

    }



}
