package com.ujiuy.pro.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Function;
import com.ujiuy.pro.bean.Module;
import com.ujiuy.pro.service.FunService;
import com.ujiuy.pro.service.MouService;
import com.ujiuy.utils.QueryObj;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
@RequestMapping("fun")
public class FunController {
    @Autowired
    FunService fs;
    @Autowired
    MouService ms;

    @RequestMapping
    public String fun(Model model, @RequestParam(defaultValue = "1") int pageNO, QueryObj queryObj) {
        System.out.println(queryObj.toString());
        PageInfo<Function> info = fs.queryObj(pageNO, queryObj);
        model.addAttribute("qo", queryObj);
        model.addAttribute("info", info);
        return "pro/project-function";
    }

    @RequestMapping("getModsByAid")
    @ResponseBody
    public List<Module> getModsByAid(Model model, int id) {
        List<Module> mlist = ms.queryMouF(id);
        return mlist;
    }

    @RequestMapping("getIdFun")
    public String getIdFun(Model m, int id) {
        Function fun = fs.getIdFun(id);
        m.addAttribute("fun", fun);
        return "pro/project-function-edit";

    }

    @RequestMapping("addFun")
    public String addFun(Model m, String newproname, Function fun) {
        String proname = newproname.split("_")[1];
        fun.setProname(proname);
        int i = fs.addFun(fun);

        return "redirect:/fun";
    }

    @RequestMapping("editFun")
    public String editFun(Model m, Function fun, String newproname) {
        String proname = newproname.split("_")[1];
        System.out.println(fun.getRemark());
        fun.setProname(proname);
        int i = fs.updateFun(fun);
        return "redirect:/fun";
    }

    @RequestMapping("getIdFunL")
    public String getIdFunL(Model m, int id) {
        Function fun = fs.getIdFun(id);
        m.addAttribute("fun", fun);
        Module mou = ms.queryMou(fun.getModeleFk());
        m.addAttribute("mou", mou);
        return "pro/project-function-look";

    }

    @RequestMapping("delFun")
    public String delFun(Model mo, @RequestParam("id") List<Integer> ids) {
        int i = fs.delFun(ids);


        return "redirect:/fun";

    }

    @RequestMapping("getFunsByFk")
    @ResponseBody
    public List<Function> getFunsByFk(Model m, int id) {
        List<Function> flist = fs.getfunK(id);
        return flist;
    }

    @RequestMapping("exportExl")
    public ResponseEntity<byte[]> exportExl() throws Exception {
        List<Function> flist = fs.getList();
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("功能信息表");
        sheet.setDefaultColumnWidth(15);
        //表头行创建
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("功能序号");
        header.createCell(1).setCellValue("功能名称");
        header.createCell(2).setCellValue("模块名称");
        header.createCell(3).setCellValue("需求名称");
        header.createCell(4).setCellValue("项目名称");
        header.createCell(5).setCellValue("优先级");
        header.createCell(6).setCellValue("详情");
        header.createCell(7).setCellValue("备注");
        //clist数据写入单元格
        for (int i = 0; i < flist.size(); i++) {
            Function fun = flist.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(fun.getId());
            row.createCell(1).setCellValue(fun.getFunctionname());
            Module m = ms.queryMou(flist.get(i).getModeleFk());
            row.createCell(2).setCellValue(m.getModname());
            row.createCell(3).setCellValue(fun.getAnalysisname());
            row.createCell(4).setCellValue(fun.getProname());
            row.createCell(5).setCellValue(fun.getLevel());
            row.createCell(6).setCellValue(fun.getSimpledis());
            row.createCell(7).setCellValue(fun.getRemark());
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        book.write(bos);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", new String("功能信息表.xls".getBytes("GBK"), "ISO-8859-1"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(bos.toByteArray(), headers, HttpStatus.OK);


    }


}
