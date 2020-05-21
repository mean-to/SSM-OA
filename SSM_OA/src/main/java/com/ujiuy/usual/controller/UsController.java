package com.ujiuy.usual.controller;


import com.ujiuy.pro.bean.Employee;
import com.ujiuy.usual.service.ArcService;
import com.ujiuy.utils.MDoc;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("us")
public class UsController {
    @Autowired
    ArcService as;
    @RequestMapping
    public ResponseEntity<byte[]> downWord(HttpSession session) throws Exception {
        Employee emp = (Employee) session.getAttribute("emp");
        int eid = emp.getEid();
        Map map = new HashMap<>();
        Employee emparc = as.arcMy(eid);
        map.put("ename",emparc.getEname());
        map.put("esex",emparc.getEsex());
        map.put("zhuanye",emparc.getArc().getZhuanye());
        map.put("school",emparc.getArc().getSchool());
        MDoc m=new MDoc();
        File file = new File("E:\\arcs","arc.doc");
        m.createDoc(map,file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", new String("arc.doc".getBytes("GBK"), "ISO-8859-1"));
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.OK);
    }

}
