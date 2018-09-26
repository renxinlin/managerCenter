package club.renxl.www.management.school.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import club.renxl.www.management.school.user.dao.domain.RolePermission;
import club.renxl.www.management.school.user.dao.domain.RolePermissionExample;

public interface RolePermissionMapper  {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    int countByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    int deleteByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    int insert(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    int insertSelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    List<RolePermission> selectByExample(RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    RolePermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RolePermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usercenter_role_permission
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RolePermission record);
}