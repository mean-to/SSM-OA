package com.ujiuy.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.usual.bean.Archives;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ArcService {
    Employee arcMy(int eid);

    int saveOrupdate(Employee emp, HttpSession session);

    PageInfo queryList(int PageNO);

    int saveMulti(List<Archives> alist);

    List<Archives> getList();

    Archives getArcByEmpId(Integer empFk2);
}
