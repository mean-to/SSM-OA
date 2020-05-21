package com.ujiuy.db.controller;

import com.ujiuy.db.bean.Datacollect;
import com.ujiuy.db.bean.Indexvalue;
import com.ujiuy.db.service.DbService;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.utils.YTime;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("db")
public class DbController {
    @Autowired
    DbService ds;

    @RequestMapping
    @ResponseBody
    public  List<Datacollect> queryDbCom(Model m){
     List<Datacollect> dlist = ds.queryDbCom();
        m.addAttribute("dlist",dlist);
        return  dlist;
    }
    @RequestMapping("getComId")
    @ResponseBody
    public Datacollect getComId(Model m,int id){
        Datacollect dc=ds.getByIdDc(id);
    return dc;
    }
    @RequestMapping("down")
    public ResponseEntity<byte []> down(String dname) throws Exception {
        File f=new File("E:\\arcs",dname);
        HttpHeaders hh=new HttpHeaders();
        hh.setContentDispositionFormData("attachment",new String(dname.getBytes("GBK"),"ISO-8859-1"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f),hh, HttpStatus.OK);
    }

    @RequestMapping("saveIndexValueInfo")
    public  String saveIndexValueInfo(Indexvalue iv, MultipartFile files, HttpSession s) throws Exception {
        File f=new File("E:\\arcs",files.getOriginalFilename());
        files.transferTo(f);
        iv.setInFile(files.getOriginalFilename());
        Employee emp=(Employee)s.getAttribute("emp");
        Integer eid = emp.getEid();
        iv.setEmpFk5(eid);
        int i=ds.saveIv(iv);
        return "db/indexvalue-base";
    }
    @RequestMapping("getYear")
    @ResponseBody
    public List<Datacollect> getYear(YTime ytime){
        List<Datacollect> dlist=ds.selectDb(ytime);

        return dlist;
    }
}
