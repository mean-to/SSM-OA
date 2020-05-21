package com.ujiuy.auth.dao;

import com.ujiuy.auth.bean.Sources;
import com.ujiuy.auth.bean.SourcesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SourcesMapper {
    long countByExample(SourcesExample example);

    int deleteByExample(SourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sources record);

    int insertSelective(Sources record);

    List<Sources> selectByExample(SourcesExample example);

    Sources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByExample(@Param("record") Sources record, @Param("example") SourcesExample example);

    int updateByPrimaryKeySelective(Sources record);

    int updateByPrimaryKey(Sources record);

    List<Sources> getSourcesPid(Map map);
}