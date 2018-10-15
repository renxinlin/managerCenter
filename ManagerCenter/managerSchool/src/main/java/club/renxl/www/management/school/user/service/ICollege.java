package club.renxl.www.management.school.user.service;

import club.renxl.www.management.school.user.dao.domain.College;
import club.renxl.www.response.BaseResponse;

/**
 * 	学校业务服务
 * @author win10
 *
 */
public interface ICollege {
	/**
	 *  获取学校信息
	 * @param college指定学校 null所有学校
	 * @return
	 */
	BaseResponse getCollegeInfo(College college);
	
	/**
	 * 	获取学校详细信息
	 * @param college
	 * @return
	 */
	BaseResponse getCollegeDetailInfo(College college);
	
}
