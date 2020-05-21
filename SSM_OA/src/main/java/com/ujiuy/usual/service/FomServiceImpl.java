package com.ujiuy.usual.service;

import com.ujiuy.pro.bean.Employee;
import com.ujiuy.pro.dao.EmployeeMapper;
import com.ujiuy.usual.bean.Evaluate;
import com.ujiuy.usual.bean.EvaluateExample;
import com.ujiuy.usual.bean.Forumpost;
import com.ujiuy.usual.bean.ForumpostExample;
import com.ujiuy.usual.dao.EvaluateMapper;
import com.ujiuy.usual.dao.ForumpostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FomServiceImpl implements FomService {
    @Autowired
    ForumpostMapper fm;
    @Autowired
    EmployeeMapper em;
    @Autowired
    EvaluateMapper evm;
    @Override
    public List<Forumpost> queryFom() {
        List<Forumpost> flist = fm.queryFom();
        return flist;
    }

    @Override
    public List<Forumpost> getFomIdList(int eid) {
        ForumpostExample fe=new ForumpostExample();
        ForumpostExample.Criteria cc = fe.createCriteria();
        cc.andEmpFk3EqualTo(eid);
        List<Forumpost> fmlist = fm.selectByExample(fe);

        return fmlist;
    }

    @Override
    public int saveFom(Forumpost fom) {
        int i = fm.insertSelective(fom);
        return i;
    }

    @Override
    public Forumpost getContent(int fid) {
        //发帖人
        Forumpost fom = fm.selectByPrimaryKey(fid);
        int empFk3 = fom.getEmpFk3();
        Employee emp = em.selectByPrimaryKey(empFk3);
        fom.setEmp(emp);
        //评论人
        EvaluateExample ee=new EvaluateExample();
        EvaluateExample.Criteria cc = ee.createCriteria();
        cc.andForumFkEqualTo(fid);
        cc.andEvaidFkIsNull();
        List<Evaluate> evlist = evm.selectByExample(ee);

        //回复人
        getSecodeFom(evlist);
        fom.setEvlist(evlist);
        return fom;
    }

    @Override
    public int evAddP(Evaluate evaluate) {
        int i = evm.insertSelective(evaluate);
        return i;
    }

    public void getSecodeFom(List<Evaluate> list){
        for (Evaluate evaluate : list) {
            evaluate.setEmp(em.selectByPrimaryKey(evaluate.getEmpFk4()));
            //获取每个评论人下的回复人
            EvaluateExample ee=new EvaluateExample();
            EvaluateExample.Criteria cc = ee.createCriteria();
            //同一个帖子
            cc.andForumFkEqualTo(evaluate.getForumFk());
            //回复的人
            cc.andEvaidFkEqualTo(evaluate.getEvaid());
            List<Evaluate> evlist = evm.selectByExample(ee);
            if(evlist!=null && evlist.size()>0){
                evaluate.setEvList(evlist);
                getSecodeFom(evlist);
            }

        }



    }

}
