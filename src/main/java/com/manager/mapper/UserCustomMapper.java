package com.manager.mapper;

import com.manager.pojo.UserCustom;
import com.manager.pojo.UserCustomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomMapper {
    int countByExample(UserCustomExample example);

    int deleteByExample(UserCustomExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCustom record);

    int insertSelective(UserCustom record);

    List<UserCustom> selectByExample(UserCustomExample example);

    UserCustom selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCustom record, @Param("example") UserCustomExample example);

    int updateByExample(@Param("record") UserCustom record, @Param("example") UserCustomExample example);

    int updateByPrimaryKeySelective(UserCustom record);

    int updateByPrimaryKey(UserCustom record);
}