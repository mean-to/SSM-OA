package com.ujiuy.pro.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Analysis;
import com.ujiuy.pro.bean.Project;
import com.ujiuy.pro.service.AnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("ana")
public class AnaController {
    @Autowired
    AnaService as;

    @RequestMapping
    public String findAnaList(Model m, @RequestParam(defaultValue = "1") int pageNO) {
        System.out.println("成功");
        PageInfo<Analysis> info = as.findAnaList(pageNO);
        m.addAttribute("info", info);
        return "pro/project-need";
    }

    @RequestMapping("getProNoAna")
    @ResponseBody
    public List<Project> getProNoAna(Model model) {
        List<Project> proList = as.getProNoAna();
        System.out.println(proList.toArray().length);
        return proList;
    }

    @RequestMapping("saveAna")
    public String saveAna(Model model, Analysis ana) {

        ana.setAddtime(new Date());
        ana.setUpdatetime(new Date());
        int i = as.saveAna(ana);
        return "redirect:/ana";

    }

    @RequestMapping("getByana")
    public String getByAna(Model m, int id) {
        Analysis ana = as.getByAna(id);
        m.addAttribute("ana", ana);
        return "pro/project-need-edit";
    }

    @RequestMapping("updateAna")
    public String updateAna(Model m, Analysis ana) {
        ana.setUpdatetime(new Date());
        int i = as.updateAna(ana);
        return "redirect:/ana";
    }

    @RequestMapping("getByIdL")
    public String getByIdL(Model m, int id) {
        Analysis ana = as.getByAna(id);
        m.addAttribute("ana", ana);
        return "pro/project-need-look";
    }

    @RequestMapping("deleAna")
    public String deleAna(Model m, @RequestParam("id") List<Integer> ids) {
        int i = as.deleAna(ids);
        return "redirect:/ana";
    }
}
