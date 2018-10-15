package club.renxl.www.management.school.user.http.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.renxl.www.management.school.user.dao.domain.College;
import club.renxl.www.management.school.user.service.ICollege;
import club.renxl.www.response.BaseResponse;

@RestController
@RequestMapping("college")
public class CollegeController {
	@Autowired
	private ICollege iCollege;

	/**
	 * 查询全部的学校信息
	 *
	 */
	@RequestMapping("look-all")
	public BaseResponse lookAll() {
		BaseResponse response = iCollege.getCollegeInfo(null);
		return response;
	}

	/**
	*  查询学校的省份信息
	*
	*/
	@RequestMapping("look-province")
	public BaseResponse lookProvince(College college) {
		BaseResponse response = iCollege.getCollegeInfo(college);
		return response;
	}

}
