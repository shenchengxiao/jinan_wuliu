package com.manager.mapper;

import com.manager.pojo.UserBinding;
import com.manager.pojo.UserBindingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBindingMapper {
    int countByExample(UserBindingExample example);

    int deleteByExample(UserBindingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserBinding record);

    int insertSelective(UserBinding record);

    List<UserBinding> selectByExample(UserBindingExample example);

    UserBinding selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserBinding record, @Param("example") UserBindingExample example);

    int updateByExample(@Param("record") UserBinding record, @Param("example") UserBindingExample example);

    int updateByPrimaryKeySelective(UserBinding record);

    int updateByPrimaryKey(UserBinding record);
}
