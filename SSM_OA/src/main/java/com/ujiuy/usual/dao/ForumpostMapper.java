package com.ujiuy.usual.dao;

import com.ujiuy.usual.bean.Forumpost;
import com.ujiuy.usual.bean.ForumpostExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ForumpostMapper {
    long countByExample(ForumpostExample example);

    int deleteByExample(ForumpostExample example);

    int deleteByPrimaryKey(Integer forumid);

    int insert(Forumpost record);

    int insertSelective(Forumpost record);

    List<Forumpost> selectByExample(ForumpostExample example);

    Forumpost selectByPrimaryKey(Integer forumid);

    int updateByExampleSelective(@Param("record") Forumpost record, @Param("example") ForumpostExample example);

    int updateByExample(@Param("record") Forumpost record, @Param("example") ForumpostExample example);

    int updateByPrimaryKeySelective(Forumpost record);

    int updateByPrimaryKey(Forumpost record);

    List<Forumpost> queryFom();
}