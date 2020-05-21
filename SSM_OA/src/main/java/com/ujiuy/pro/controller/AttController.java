package com.ujiuy.pro.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Attachment;
import com.ujiuy.pro.bean.Project;
import com.ujiuy.pro.service.AttService;
import com.ujiuy.pro.service.ProService;
import com.ujiuy.utils.QueryObj;
import org.apache.commons.io.FileUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("file")
public class AttController {
    @Autowired
    AttService as;
    @Autowired
    ProService ps;

    @RequestMapping
    public String queryObj(Model m, @RequestParam(defaultValue = "1") int pageNO, QueryObj queryObj) {

        PageInfo<Attachment> info = as.queryObj(pageNO, queryObj);

        m.addAttribute("info", info);
        m.addAttribute("qo", queryObj);
        return "pro/project-file";
    }

    @RequestMapping("getAllFile")
    @ResponseBody
    public List<Project> getAllFile(Model m) {

        List<Project> plist = ps.queryAll();
        System.out.println(plist.get(0).getPname());
        return plist;
    }

    @RequestMapping("saveInfo")
    public String saveInfo(Model m, Attachment att, MultipartFile files) {

        //上传到服务器
        String newname = UUID.randomUUID().toString().substring(24) + "_" + files.getOriginalFilename();
        File f = new File("E:\\crmtext", newname);

        try {
            files.transferTo(f);
            att.setPath(newname);
            //保存到数据库
            att.setAddtime(new Date());
            att.setUpdatetime(new Date());
            as.saveAtt(att);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/file";
    }

    @RequestMapping("getIdFile")
    public String getIdFile(Model model, int id) {
        //根据主键获取附件对象
        Attachment att = as.getIdFile(id);
        model.addAttribute("att", att);
        Project pro = ps.getIdPos(att.getProFk());
        model.addAttribute("pro", pro);
        return "pro/project-file-edit";
    }

    @RequestMapping("updateFile")
    public String updateFile(Model m, Attachment att, MultipartFile files) {
        //判断是否上传附件，否则保留原附件
        long size = files.getSize();
        if (size > 0) {
            File oldfile = new File("E:\\crmtext", att.getPath());
            if (oldfile.exists()) {
                oldfile.delete();
            }
            //上传新附件
            String newname = UUID.randomUUID().toString().substring(24) + "_" + files.getOriginalFilename();
            new File("E:\\crmtext", newname);
            att.setPath(newname);
        }
        att.setUpdatetime(new Date());
        int i = as.uptadeFile(att);
        return "redirect:/file";


    }

    @RequestMapping("download")
    public ResponseEntity<byte[]> download(String path) throws Exception {
        File file = new File("E:\\crmtext", path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", path.substring(13));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);


    }

    @RequestMapping("delfil")
    public String delFil(Model m, @RequestParam("id") List<Integer> ids) {
        int i = as.delMulti(ids);
        return "redirect:/file";


    }


}
