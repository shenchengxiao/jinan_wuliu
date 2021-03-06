package com.manager.mapper;

import com.manager.pojo.ClientUpgradeInfo;
import com.manager.pojo.ClientUpgradeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientUpgradeInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    int countByExample(ClientUpgradeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    int deleteByExample(ClientUpgradeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    int insert(ClientUpgradeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    int insertSelective(ClientUpgradeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    List<ClientUpgradeInfo> selectByExample(ClientUpgradeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    ClientUpgradeInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    int updateByExampleSelective(@Param("record") ClientUpgradeInfo record, @Param("example") ClientUpgradeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    int updateByExample(@Param("record") ClientUpgradeInfo record, @Param("example") ClientUpgradeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    int updateByPrimaryKeySelective(ClientUpgradeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table client_upgrade_info
     *
     * @mbggenerated Thu Apr 13 17:22:56 CST 2017
     */
    int updateByPrimaryKey(ClientUpgradeInfo record);
}