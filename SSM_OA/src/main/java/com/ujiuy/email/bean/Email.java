package com.ujiuy.email.bean;

import com.ujiuy.pro.bean.Employee;

import java.util.Date;

public class Email {
    private Integer id;

    private String title;

    private Integer empFk2;

    private Date sendtime;

    private String content;

    private Integer empFk;

    private String path;
    private Employee emp;

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getEmpFk2() {
        return empFk2;
    }

    public void setEmpFk2(Integer empFk2) {
        this.empFk2 = empFk2;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getEmpFk() {
        return empFk;
    }

    public void setEmpFk(Integer empFk) {
        this.empFk = empFk;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}