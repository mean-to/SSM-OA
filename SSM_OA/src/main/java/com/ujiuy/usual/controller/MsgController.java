package com.ujiuy.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuy.pro.bean.Employee;
import com.ujiuy.pro.service.EmpService;
import com.ujiuy.usual.bean.Msg;
import com.ujiuy.usual.dao.MsgMapper;
import com.ujiuy.usual.service.MsgService;
import com.ujiuy.utils.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("msg")
public class MsgController {
    @Autowired
    MsgService ms;
    @Autowired
    EmpService es;
    @Autowired
    MsgMapper mm;
    @RequestMapping
    public String query(Model model, @RequestParam(defaultValue = "1") int pageNO){
        System.out.println(pageNO);
        PageInfo<Msg> info =ms.queryMsgList(pageNO);
        model.addAttribute("info",info);
        return "usual/message-give";
    }

    @RequestMapping("getEmpNotId")
    @ResponseBody
    public List<Employee> getEmpNotId(HttpSession session){
        Employee em = (Employee) session.getAttribute("emp");
        int  eid = em.getEid();
        List<Employee> elist = ms.getEmpNotId(eid);
        return elist;
    }
    @RequestMapping("saveMsg")
    public String saveMsg(Model m,Msg msg,HttpSession session) throws Exception {
        Employee em = (Employee) session.getAttribute("emp");
        int eid = em.getEid();
        msg.setSendp(eid);
        msg.setMark(0);
        //Jobdetail
        JobDetail detail = JobBuilder.newJob(MyJob.class).withIdentity("job1", "g1").build();
        JobDataMap map = detail.getJobDataMap();//JobDataMap:将数据传递到任务对象里（MsgJob）
        map.put("msg",msg);
        map.put("mm",mm);

        //简单触发器
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("tri1", "g1").
                withSchedule(SimpleScheduleBuilder.simpleSchedule()).startAt(msg.getMsgtime())
                .build();

        //调度器
        SchedulerFactory sf=new StdSchedulerFactory();//调度器工厂
        Scheduler scheduler = sf.getScheduler();//通过调度器工厂获得调度器

        scheduler.scheduleJob(detail,trigger);
        scheduler.start();
        return "redirect:/msg";
    }

}
