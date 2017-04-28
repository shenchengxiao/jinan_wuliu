package com.manager.mapper;

import com.manager.pojo.PhoneRecord;
import com.manager.pojo.PhoneRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRecordMapper {
    int countByExample(PhoneRecordExample example);

    int deleteByExample(PhoneRecordExample example);

    int deleteByPrimaryKey(Integer rId);

    int insert(PhoneRecord record);

    int insertSelective(PhoneRecord record);

    List<PhoneRecord> selectByExample(PhoneRecordExample example);

    PhoneRecord selectByPrimaryKey(Integer rId);

    int updateByExampleSelective(@Param("record") PhoneRecord record, @Param("example") PhoneRecordExample example);

    int updateByExample(@Param("record") PhoneRecord record, @Param("example") PhoneRecordExample example);

    int updateByPrimaryKeySelective(PhoneRecord record);

    int updateByPrimaryKey(PhoneRecord record);
}
