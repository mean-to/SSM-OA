package com.ujiuy.cus.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuy.cus.bean.Customer;
import com.ujiuy.cus.bean.CustomerExample;
import com.ujiuy.cus.dao.CustomerMapper;
import com.ujiuy.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CusServiceImpl implements CusService {

    @Autowired
    CustomerMapper cm;
    @Override
    public PageInfo<Customer> getBypage(int pageNO,QueryObj queryObj) {
        PageHelper.startPage(pageNO,4);
         List<Customer> clist=cm.queryobj(queryObj);
        PageInfo info=new PageInfo(clist);
        return info;
    }

    @Override
    public int saveCus(Customer cus) {


       cus.setAddtime(new Date());
        int i = cm.insertSelective(cus);


        return i;
    }

    @Override
    public Customer selectId(int cid) {
        Customer cus = cm.selectByPrimaryKey(cid);

        return cus;
    }

    @Override
    public int updateCus(Customer cus) {
        int i = cm.updateByPrimaryKey(cus);
        return i;
    }

    @Override
    public int delIds(List<Integer> ids) {
        CustomerExample ce=new CustomerExample();
        CustomerExample.Criteria cc = ce.createCriteria();
        cc.andIdIn(ids);
        int i = cm.deleteByExample(ce);
        return i;
    }

    @Override
    public List<Customer> getList() {
        List<Customer> clist= cm.selectByExample(null);
        return clist;
    }
}
