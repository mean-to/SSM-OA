package com.ujiuy.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.usual.bean.Notice;
import com.ujiuy.usual.bean.Task;
import com.ujiuy.usual.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("notice")
public class NoticeController {
    @Autowired
    NoticeService ns;
    @RequestMapping
    public String queryNotice(Model m,@RequestParam(defaultValue = "1") int pageNO){
      PageInfo<Notice> info = ns.queryNotice(pageNO);
        m.addAttribute("info",info);
        return "usual/notice";

    }
    @RequestMapping("getNotList")
    @ResponseBody
    public List<Notice> getNotList(){
       return ns.getNotList();
    }
    @RequestMapping("getTaskNid")
    @ResponseBody
    public Notice getTaskNid(int nid){
        System.out.println(nid);
        Notice notice=ns.getTaskNid(nid);

        return notice;
    }
}
