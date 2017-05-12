package com.manager.mapper;

import com.manager.pojo.Deletehistory;
import com.manager.pojo.DeletehistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface DeletehistoryMapper {
    int countByExample(DeletehistoryExample example);

    int deleteByExample(DeletehistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Deletehistory record);

    int insertSelective(Deletehistory record);

    List<Deletehistory> selectByExample(DeletehistoryExample example);

    Deletehistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Deletehistory record, @Param("example") DeletehistoryExample example);

    int updateByExample(@Param("record") Deletehistory record, @Param("example") DeletehistoryExample example);

    int updateByPrimaryKeySelective(Deletehistory record);

    int updateByPrimaryKey(Deletehistory record);
}