package com.ujiuy.pro.dao;

import com.ujiuy.pro.bean.Function;
import com.ujiuy.pro.bean.FunctionExample;
import com.ujiuy.utils.QueryObj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FunctionMapper {
    long countByExample(FunctionExample example);

    int deleteByExample(FunctionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Function record);

    int insertSelective(Function record);

    List<Function> selectByExample(FunctionExample example);

    Function selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Function record, @Param("example") FunctionExample example);

    int updateByExample(@Param("record") Function record, @Param("example") FunctionExample example);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);

    List<Function> queryObj(QueryObj queryObj);


}