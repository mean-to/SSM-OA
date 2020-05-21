package com.ujiuy.pro.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Analysis;
import com.ujiuy.pro.bean.Module;
import com.ujiuy.pro.service.MouService;
import com.ujiuy.pro.service.ProService;
import com.ujiuy.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("mou")
public class MouController {
    @Autowired
    MouService ms;
    @Autowired
    ProService ps;

    @RequestMapping
    public String queryObj(Model m, @RequestParam(defaultValue = "1") int pageNO, QueryObj queryObj) {
        System.out.println(queryObj.toString());
        PageInfo<Module> info = ms.queryObj(pageNO, queryObj);
        m.addAttribute("info", info);
        m.addAttribute("qo", queryObj);
        return "pro/project-model";
    }

    @RequestMapping("getMouPro")
    @ResponseBody
    public List<Analysis> getMouPro() {
        System.out.println("111111111111");
        List<Analysis> alist = ps.getMouProHas();
        return alist;
    }

    @RequestMapping("saveInfo")
    public String saveInfo(Model m, String newproname, Module mou) {
        String s = newproname.split("_")[1];
        mou.setProname(s);
        int i = ms.saeInfo(mou);
        return "redirect:/mou";
    }

    @RequestMapping("getBymouId")
    public String getBymouId(Model m, int id) {
        Module mou = ms.queryMou(id);
        m.addAttribute("mou", mou);
        return "pro/project-model-edit";
    }

    @RequestMapping("updateMou")
    public String updateMou(Model model, Module mou, String newproname) {
        String proname = newproname.split("_")[1];
        mou.setProname(proname);
        int i = ms.updateMou(mou);

        return "redirect:/mou";
    }

    @RequestMapping("getBymouIdL")
    public String getBymouIdL(Model m, int id) {
        Module mou = ms.queryMouL(id);
        m.addAttribute("mou", mou);
        return "pro/project-model-look";
    }

}




