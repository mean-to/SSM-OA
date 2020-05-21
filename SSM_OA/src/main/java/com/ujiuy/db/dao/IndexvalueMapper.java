package com.ujiuy.db.dao;

import com.ujiuy.db.bean.Indexvalue;
import com.ujiuy.db.bean.IndexvalueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndexvalueMapper {
    long countByExample(IndexvalueExample example);

    int deleteByExample(IndexvalueExample example);

    int deleteByPrimaryKey(Integer inId);

    int insert(Indexvalue record);

    int insertSelective(Indexvalue record);

    List<Indexvalue> selectByExample(IndexvalueExample example);

    Indexvalue selectByPrimaryKey(Integer inId);

    int updateByExampleSelective(@Param("record") Indexvalue record, @Param("example") IndexvalueExample example);

    int updateByExample(@Param("record") Indexvalue record, @Param("example") IndexvalueExample example);

    int updateByPrimaryKeySelective(Indexvalue record);

    int updateByPrimaryKey(Indexvalue record);
}