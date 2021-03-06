package club.renxl.www.management.school.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import club.renxl.www.management.school.user.dao.domain.College;
import club.renxl.www.management.school.user.dao.domain.CollegeExample;

public interface CollegeMapper   {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    int countByExample(CollegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    int deleteByExample(CollegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Short coid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    int insert(College record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    int insertSelective(College record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    List<College> selectByExample(CollegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    College selectByPrimaryKey(Short coid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") College record, @Param("example") CollegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") College record, @Param("example") CollegeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(College record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table college
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(College record);
    /**
     * 	获取学校以及学校所在省份信息
     * @param college
     * @return
     */
	List<College> selectCollegeWithProvinceInfoByCollegeCondition(College college);
}