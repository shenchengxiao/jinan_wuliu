package com.manager.mapper;

import com.manager.pojo.IpVisit;
import com.manager.pojo.IpVisitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repositorypublic interface IpVisitMapper {
    int countByExample(IpVisitExample example);

    int deleteByExample(IpVisitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IpVisit record);

    int insertSelective(IpVisit record);

    List<IpVisit> selectByExample(IpVisitExample example);

    IpVisit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IpVisit record, @Param("example") IpVisitExample example);

    int updateByExample(@Param("record") IpVisit record, @Param("example") IpVisitExample example);

    int updateByPrimaryKeySelective(IpVisit record);

    int updateByPrimaryKey(IpVisit record);
}
