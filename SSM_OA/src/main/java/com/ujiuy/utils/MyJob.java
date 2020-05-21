package com.ujiuy.utils;

import com.ujiuy.usual.bean.Msg;
import com.ujiuy.usual.dao.MsgMapper;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("保存信息。。。。");
        //通过JobDataMap对象获取控制层传来的数据
        JobDataMap map = context.getJobDetail().getJobDataMap();
        Msg msg= (Msg) map.get("msg");
        MsgMapper mm= (MsgMapper) map.get("mm");
        mm.insertSelective(msg);
    }
}
