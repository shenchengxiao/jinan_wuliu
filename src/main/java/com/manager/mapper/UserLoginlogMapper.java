package com.manager.mapper;

import com.manager.pojo.UserLoginlog;
import com.manager.pojo.UserLoginlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLoginlogMapper {
    int countByExample(UserLoginlogExample example);

    int deleteByExample(UserLoginlogExample example);

    int deleteByPrimaryKey(Integer loginLogId);

    int insert(UserLoginlog record);

    int insertSelective(UserLoginlog record);

    List<UserLoginlog> selectByExample(UserLoginlogExample example);

    UserLoginlog selectByPrimaryKey(Integer loginLogId);

    int updateByExampleSelective(@Param("record") UserLoginlog record, @Param("example") UserLoginlogExample example);

    int updateByExample(@Param("record") UserLoginlog record, @Param("example") UserLoginlogExample example);

    int updateByPrimaryKeySelective(UserLoginlog record);

    int updateByPrimaryKey(UserLoginlog record);
}