package com.ujiuy.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.pro.service.EmpService;
import com.ujiuy.usual.bean.Archives;
import com.ujiuy.usual.service.ArcService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("arc")
public class ArcController {
    @Autowired
    ArcService as;
    @Autowired
    EmpService es;

    @RequestMapping
    public String queryList(Model m,@RequestParam(defaultValue = "1") int pageNO){
        PageInfo info = as.queryList(pageNO);
        m.addAttribute("info",info);
        return "usual/archives";
    }


@RequestMapping("arcmy")
    public  String arcmy(Model m, HttpSession session){
    Employee em = (Employee) session.getAttribute("emp");
    int eid = em.getEid();
    Employee emp = as.arcMy(eid);
    m.addAttribute("emp",emp);
    System.out.println(emp.getPos().getName());

    return "usual/myarchives";
}
@RequestMapping("saveInfo")
    public String saveOrupdate(Model m,Employee emp,HttpSession session){
    System.out.println(emp.getArc().getDnum());
    int i = as.saveOrupdate(emp,session);
    return "redirect:/arc";
}
@RequestMapping("getByArc")
public  String getByArc(Model m,int id){
    System.out.println(id+"=======================================");
    Employee emp = as.arcMy(id);
    m.addAttribute("emp",emp);
    return "usual/myarchives";
}
@RequestMapping("addArchives")
    public  String addArchives(Model m, MultipartFile files) throws Exception {
    //解析excel----->档案的集合------>批量插入
    InputStream is = files.getInputStream();
    //excel对象读取excel文件流
    HSSFWorkbook book=new HSSFWorkbook(is);
    List<Archives> alist=new ArrayList<>();
    //遍历工作薄
    for (int i = 0; i <book.getNumberOfSheets() ; i++) {
        HSSFSheet sheet = book.getSheetAt(i);
        if(sheet==null){
            continue;
        }
        //遍历行
        for (int j = 0; j <sheet.getLastRowNum() ; j++) {
            HSSFRow row = sheet.getRow(j+1);
            if(row!=null){
                Archives arc=new Archives();
                arc.setDnum(row.getCell(0).getStringCellValue());
                arc.setLandline(row.getCell(1).getStringCellValue());
                arc.setSchool(row.getCell(2).getStringCellValue());
                arc.setZhuanye(row.getCell(3).getStringCellValue());
                arc.setSosperson(row.getCell(4).getStringCellValue());
                arc.setBiyedate(row.getCell(5).getDateCellValue());
                arc.setZzmm(row.getCell(6).getStringCellValue());
                arc.setMinzu(row.getCell(7).getStringCellValue());
                arc.setXueli(row.getCell(8).getStringCellValue());
                arc.setEmail(row.getCell(9).getStringCellValue());
                arc.setEmpFk((int)row.getCell(10).getNumericCellValue());
                arc.setRemark(row.getCell(11).getStringCellValue());
                arc.setHirdate(row.getCell(12).getDateCellValue());
                alist.add(arc);
            }
        }
    }
    int i=as.saveMulti(alist);
        return "redirect:/arc";
}
    @RequestMapping("exportExl")
    public ResponseEntity<byte[]> exportExl() throws Exception {
        List<Archives> alist = as.getList();
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("档案表");
        sheet.setDefaultColumnWidth(15);
        //表头行创建
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("档案序号");
        header.createCell(1).setCellValue("固定电话");
        header.createCell(2).setCellValue("大学");
        header.createCell(3).setCellValue("专业");
        header.createCell(4).setCellValue("紧急联系人");
        header.createCell(5).setCellValue("生日");
        header.createCell(6).setCellValue("zzmm");
        header.createCell(7).setCellValue("民族");
        header.createCell(8).setCellValue("学历");
        header.createCell(9).setCellValue("邮箱");
        header.createCell(10).setCellValue("名字");
        header.createCell(11).setCellValue("备注");
        header.createCell(12).setCellValue("时间");

        //clist数据写入单元格
        for (int i = 0; i < alist.size(); i++) {
            Archives fun = alist.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(fun.getDnum());
            row.createCell(1).setCellValue(fun.getLandline());
            Employee e = es.queryEMp(alist.get(i).getEmpFk());
            row.createCell(2).setCellValue(fun.getSchool());
            row.createCell(3).setCellValue(fun.getZhuanye());
            row.createCell(4).setCellValue(fun.getSosperson());
            row.createCell(5).setCellValue( new SimpleDateFormat("yyyy-MM-dd").format(fun.getBiyedate()));
            row.createCell(6).setCellValue(fun.getZzmm());
            row.createCell(7).setCellValue(fun.getMinzu());
            row.createCell(8).setCellValue(fun.getXueli());
            row.createCell(9).setCellValue(fun.getEmail());
            row.createCell(10).setCellValue(e.getEname());
            row.createCell(11).setCellValue(fun.getRemark());
            row.createCell(12).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(fun.getHirdate()));
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        book.write(bos);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", new String("档案表.xls".getBytes("GBK"), "ISO-8859-1"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(bos.toByteArray(), headers, HttpStatus.OK);


    }





}
