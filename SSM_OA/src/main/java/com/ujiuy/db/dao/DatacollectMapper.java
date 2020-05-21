package com.ujiuy.db.dao;

import com.ujiuy.db.bean.Datacollect;
import com.ujiuy.db.bean.DatacollectExample;
import com.ujiuy.utils.YTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DatacollectMapper {
    long countByExample(DatacollectExample example);

    int deleteByExample(DatacollectExample example);

    int deleteByPrimaryKey(Integer daid);

    int insert(Datacollect record);

    int insertSelective(Datacollect record);

    List<Datacollect> selectByExample(DatacollectExample example);

    Datacollect selectByPrimaryKey(Integer daid);

    int updateByExampleSelective(@Param("record") Datacollect record, @Param("example") DatacollectExample example);

    int updateByExample(@Param("record") Datacollect record, @Param("example") DatacollectExample example);

    int updateByPrimaryKeySelective(Datacollect record);

    int updateByPrimaryKey(Datacollect record);

    List<Datacollect> queryDbcom();

    List<Datacollect> selectDBTime(YTime ytime);
}