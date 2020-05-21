package com.ujiuy.pro.dao;

import com.ujiuy.pro.bean.Module;
import com.ujiuy.pro.bean.ModuleExample;
import com.ujiuy.utils.QueryObj;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleMapper {
    long countByExample(ModuleExample example);

    int deleteByExample(ModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    int insertSelective(Module record);

    List<Module> selectByExample(ModuleExample example);

    Module selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByExample(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);

    List<Module> queryObj(QueryObj queryObj);

    List<Module> getMouPro();


    Module queryMouL(int id);
}