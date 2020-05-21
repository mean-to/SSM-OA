package com.ujiuy.usual.dao;

import com.ujiuy.usual.bean.Evaluate;
import com.ujiuy.usual.bean.EvaluateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EvaluateMapper {
    long countByExample(EvaluateExample example);

    int deleteByExample(EvaluateExample example);

    int deleteByPrimaryKey(Integer evaid);

    int insert(Evaluate record);

    int insertSelective(Evaluate record);

    List<Evaluate> selectByExample(EvaluateExample example);

    Evaluate selectByPrimaryKey(Integer evaid);

    int updateByExampleSelective(@Param("record") Evaluate record, @Param("example") EvaluateExample example);

    int updateByExample(@Param("record") Evaluate record, @Param("example") EvaluateExample example);

    int updateByPrimaryKeySelective(Evaluate record);

    int updateByPrimaryKey(Evaluate record);
}