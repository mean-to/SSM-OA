package com.ujiuy.cus.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.cus.bean.Customer;
import com.ujiuy.cus.service.CusService;
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

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("cus")
public class CusController {

    @Autowired
    CusService cs;

    @RequestMapping
    public String cus(Model m, @RequestParam(defaultValue = "1") int pageNO, QueryObj queryObj) {
        PageInfo<Customer> info = cs.getBypage(pageNO, queryObj);
        m.addAttribute("info", info);
        m.addAttribute("qo", queryObj);
        return "cus/customer";

    }

    @RequestMapping("saveInfo")
    public String saveCus(Model m, Customer cus) {

        int i = cs.saveCus(cus);
        return "redirect:/cus";
    }

    @RequestMapping("getCusbyId")
    public String getCusbyId(Model m, int cid, @RequestParam(defaultValue = "0") int fl) {
        Customer cus = cs.selectId(cid);
        m.addAttribute("cus", cus);
        if (fl != 0) {
            return "cus/customer-look";
        } else {
            return "cus/customer-edit";

        }


    }

    @RequestMapping("editSave")
    public String editSave(Model m, Customer cus) {
        int i = cs.updateCus(cus);
        return "redirect:/cus";
    }

    @RequestMapping("delcus")
    public String delcus(Model model, @RequestParam("id") List<Integer> ids) {
        int i = cs.delIds(ids);
        System.out.println(ids);
        return "redirect:/cus";
    }

    @RequestMapping("exportExl")
    public ResponseEntity<byte[]> exportExl() throws Exception {
        List<Customer> clist = cs.getList();
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("客户信息表");
        sheet.setDefaultColumnWidth(15);
        //表头行创建
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("职工序号");
        header.createCell(1).setCellValue("联系人姓名");
        header.createCell(2).setCellValue("公司名称");
        header.createCell(3).setCellValue("添加时间");
        header.createCell(4).setCellValue("联系电话");
        //clist数据写入单元格
        for (int i = 0; i < clist.size(); i++) {
            Customer cus = clist.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(cus.getId());
            row.createCell(1).setCellValue(cus.getCompanyperson());
            row.createCell(2).setCellValue(cus.getComname());
            row.createCell(3).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(cus.getAddtime()));

            row.createCell(4).setCellValue(cus.getComphone());
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        book.write(bos);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", new String("客户列表.xls".getBytes("GBK"), "ISO-8859-1"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(bos.toByteArray(), headers, HttpStatus.OK);
    }

}




