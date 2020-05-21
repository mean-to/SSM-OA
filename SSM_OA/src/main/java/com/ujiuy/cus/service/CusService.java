package com.ujiuy.cus.service;

import com.github.pagehelper.PageInfo;
import com.ujiuy.cus.bean.Customer;
import com.ujiuy.utils.QueryObj;

import java.util.List;

public interface CusService {
    public PageInfo<Customer> getBypage(int pageNO,QueryObj queryObj);



    int saveCus(Customer cus);

    Customer selectId(int cid);

    int updateCus(Customer cus);

    int delIds(List<Integer> ids);
    List<Customer> getList();
}
