package com.ujiuy.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.usual.bean.Msg;

import java.util.List;

public interface MsgService {
   PageInfo<Msg> queryMsgList(int pageNO);

    List<Employee> getEmpNotId(int eid);
}
