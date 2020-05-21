package com.ujiuy.usual.dao;

import com.ujiuy.usual.bean.Archives;
import com.ujiuy.usual.bean.ArchivesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArchivesMapper {
    long countByExample(ArchivesExample example);

    int deleteByExample(ArchivesExample example);

    int deleteByPrimaryKey(String dnum);

    int insert(Archives record);

    int insertSelective(Archives record);

    List<Archives> selectByExample(ArchivesExample example);

    Archives selectByPrimaryKey(String dnum);

    int updateByExampleSelective(@Param("record") Archives record, @Param("example") ArchivesExample example);

    int updateByExample(@Param("record") Archives record, @Param("example") ArchivesExample example);

    int updateByPrimaryKeySelective(Archives record);

    int updateByPrimaryKey(Archives record);
    Archives getArcEid(Integer eid);


    int saveMulti(List<Archives> alist);
}