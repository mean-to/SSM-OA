package com.ujiuy.pro.dao;

import com.ujiuy.pro.bean.Analysis;
import com.ujiuy.pro.bean.AnalysisExample;
import com.ujiuy.usual.bean.Archives;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnalysisMapper {
    long countByExample(AnalysisExample example);

    int deleteByExample(AnalysisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Analysis record);

    int insertSelective(Analysis record);

    List<Analysis> selectByExample(AnalysisExample example);

    Analysis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Analysis record, @Param("example") AnalysisExample example);

    int updateByExample(@Param("record") Analysis record, @Param("example") AnalysisExample example);

    int updateByPrimaryKeySelective(Analysis record);

    int updateByPrimaryKey(Analysis record);

}