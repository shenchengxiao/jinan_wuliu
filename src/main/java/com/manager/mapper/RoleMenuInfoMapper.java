package com.manager.mapper;

import com.manager.pojo.RoleMenuInfo;
import com.manager.pojo.RoleMenuInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleMenuInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    int countByExample(RoleMenuInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    int deleteByExample(RoleMenuInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    int insert(RoleMenuInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    int insertSelective(RoleMenuInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    List<RoleMenuInfo> selectByExample(RoleMenuInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    RoleMenuInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    int updateByExampleSelective(@Param("record") RoleMenuInfo record, @Param("example") RoleMenuInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    int updateByExample(@Param("record") RoleMenuInfo record, @Param("example") RoleMenuInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    int updateByPrimaryKeySelective(RoleMenuInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Fri Mar 31 11:32:07 CST 2017
     */
    int updateByPrimaryKey(RoleMenuInfo record);
}