package com.ujiuy.pro.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.cus.bean.Customer;
import com.ujiuy.cus.service.CusService;
import com.ujiuy.pro.bean.Project;
import com.ujiuy.pro.service.EmpService;
import com.ujiuy.pro.service.ProService;
import com.ujiuy.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("pro")
public class ProController {
    @Autowired
    ProService ps;
    @Autowired
    CusService cs;
    @Autowired
    EmpService es;

    @RequestMapping
    public String queryObj(Model m, QueryObj queryObj, @RequestParam(defaultValue = "1") int pageNO) {
        System.out.println(queryObj.toString());
        PageInfo info = ps.queryObj(pageNO, queryObj);
        m.addAttribute("info", info);
        m.addAttribute("qo", queryObj);
        return "pro/project-base";
    }

    @RequestMapping("toadd")
    public String toadd(Model model, Project pro) {
        List<Customer> clist = cs.getList();
        //客户公司列表
        model.addAttribute("clist", clist);
        //查询项目经理列表
        model.addAttribute("elist", es.getEmpsByPos(4));


        System.out.println(pro.getBuildtime() + "" + pro.getEndtime());
        return "pro/project-base-add";
    }

    @RequestMapping("saveInfo")
    public String saveInfo(Model model, Project pro, String newcomname) {
        pro.setComname(Integer.parseInt(newcomname.split("_")[0]));
        int i = ps.saveInfo(pro);

        return "redirect:/pro";


    }

    @RequestMapping("getIdPos")
    public String getIdPos(Model model, int pid) {
        List<Customer> clist = cs.getList();
        //客户公司列表
        model.addAttribute("clist", clist);
        //查询项目经理列表
        model.addAttribute("elist", es.getEmpsByPos(4));
        Project pro = ps.getIdPos(pid);
        model.addAttribute("pro", pro);
        return "pro/project-base-edit";
    }

    @RequestMapping("updatePro")
    public String updatePro(Model model, Project pro, String newcomname) {
        System.out.println(pro.getLevel());
        pro.setComname(Integer.parseInt(newcomname.split("_")[0]));
        ps.updatePro(pro);

        return "redirect:/pro";
    }

    @RequestMapping("getbyIdPos")
    public String getbyIdPos(Model model, int pid) {

        Project pro = ps.getByIdPos(pid);


        model.addAttribute("pro", pro);
        return "pro/project-base-look";
    }

    @RequestMapping("delbase")
    public String delbase(Model m, @RequestParam("pid") List<Integer> ids) {
        int i = ps.delbase(ids);
        return "redirect:/pro";
    }


}
