package com.ujiuy.usual.dao;

import com.ujiuy.usual.bean.Baoxiao;
import com.ujiuy.usual.bean.BaoxiaoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaoxiaoMapper {
    long countByExample(BaoxiaoExample example);

    int deleteByExample(BaoxiaoExample example);

    int deleteByPrimaryKey(String bxid);

    int insert(Baoxiao record);

    int insertSelective(Baoxiao record);

    List<Baoxiao> selectByExample(BaoxiaoExample example);

    Baoxiao selectByPrimaryKey(String bxid);

    int updateByExampleSelective(@Param("record") Baoxiao record, @Param("example") BaoxiaoExample example);

    int updateByExample(@Param("record") Baoxiao record, @Param("example") BaoxiaoExample example);

    int updateByPrimaryKeySelective(Baoxiao record);

    int updateByPrimaryKey(Baoxiao record);

    List<Baoxiao> queryBxList(int flag);
}