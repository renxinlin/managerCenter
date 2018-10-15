package club.renxl.www.management.school.user.service;

import club.renxl.www.management.school.user.dao.domain.Area;
import club.renxl.www.response.BaseResponse;

/**
 * 	省区查询
 *	字典服务
 * @author win10
 *
 */
public interface IArea {
	/**
	 *	查询省区数据
	 * @param area名称精确查找 null查找所有
	 * @return
	 */
	BaseResponse lookAll(Area area);
	
	
}
