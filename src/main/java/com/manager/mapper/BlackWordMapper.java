package com.manager.mapper;

import com.manager.pojo.BlackWord;
import com.manager.pojo.BlackWordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackWordMapper {
    int countByExample(BlackWordExample example);

    int deleteByExample(BlackWordExample example);

    int deleteByPrimaryKey(Integer bWId);

    int insert(BlackWord record);

    int insertSelective(BlackWord record);

    List<BlackWord> selectByExample(BlackWordExample example);

    BlackWord selectByPrimaryKey(Integer bWId);

    int updateByExampleSelective(@Param("record") BlackWord record, @Param("example") BlackWordExample example);

    int updateByExample(@Param("record") BlackWord record, @Param("example") BlackWordExample example);

    int updateByPrimaryKeySelective(BlackWord record);

    int updateByPrimaryKey(BlackWord record);
}
