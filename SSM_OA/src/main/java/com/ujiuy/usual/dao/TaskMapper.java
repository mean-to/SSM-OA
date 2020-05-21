package com.ujiuy.usual.dao;

import com.ujiuy.auth.bean.Sources;
import com.ujiuy.usual.bean.Task;
import com.ujiuy.usual.bean.TaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TaskMapper {
    long countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    List<Task> queryObj(Map map);

    List<Task> queryObjMy(Map map);

    List<Sources> getSourcesPid(Map map);
}