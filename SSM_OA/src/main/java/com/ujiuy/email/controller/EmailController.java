package com.ujiuy.email.controller;

import com.ujiuy.email.bean.Email;
import com.ujiuy.email.service.EmailService;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.usual.service.ArcService;
import com.ujiuy.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("email")

public class EmailController {
    @Autowired
    EmailService es;
    @Autowired
    ArcService as;

    @RequestMapping
    public String Email(Model m,HttpSession session,@RequestParam(defaultValue = "0") int flag){
        Employee emp = (Employee) session.getAttribute("emp");
        Integer eid = emp.getEid();
        List<Email> elist = es.queryEmail(eid,flag);
        System.out.println("+++++++++++++++++++++++++"+flag);
        m.addAttribute("elist",elist);
        m.addAttribute("flag",flag);
        return "email/email";

    }


    @RequestMapping("getEmailfEmps")
    @ResponseBody
    public List<Employee> getEmailEmps(Model m, HttpSession session) {
        Employee emp = (Employee) session.getAttribute("emp");
        int eid = emp.getEid();
        List<Employee> elist = es.getEmailEmps(eid);
        return elist;
    }

    @RequestMapping("saveEma")
    public String saveEma(Model m, Email email, MultipartFile myfj, HttpSession session) throws IOException {
        long size = myfj.getSize();
        File temp = null;
        if (size > 0) {
            temp = new File("E:\\arcs", myfj.getOriginalFilename());
            myfj.transferTo(temp);
            email.setPath(myfj.getOriginalFilename());
        }
        email.setSendtime(new Date());
        Employee emp = (Employee) session.getAttribute("emp");
        email.setEmpFk(emp.getEid());
        es.saveEmail(email);
        //发送邮件
        //获取收件人的邮箱
        String revpath = as.getArcByEmpId(email.getEmpFk2()).getEmail();
        if (size > 0) {
            MailUtils.sendAttachmentMail(revpath,email.getTitle(), email.getContent(), temp);
        } else {
            MailUtils.sendSimpleMail(revpath, email.getTitle(), email.getContent());
        }
        return "redirect:/email";


    }
}
