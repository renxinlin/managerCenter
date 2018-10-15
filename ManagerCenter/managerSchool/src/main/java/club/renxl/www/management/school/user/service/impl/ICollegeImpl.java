package club.renxl.www.management.school.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import club.renxl.www.common.constants.Symbol;
import club.renxl.www.management.school.user.dao.CollegeMapper;
import club.renxl.www.management.school.user.dao.domain.College;
import club.renxl.www.management.school.user.dao.domain.CollegeExample;
import club.renxl.www.management.school.user.dao.domain.CollegeExample.Criteria;
import club.renxl.www.management.school.user.service.ICollege;
import club.renxl.www.response.BaseResponse;

@Service
public class ICollegeImpl implements ICollege {
	@Autowired
	private CollegeMapper collegeMapper;
	
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.ICollege#getCollegeInfo(club.renxl.www.management.school.user.dao.domain.College)
	 */
	@Override
	public BaseResponse getCollegeInfo(College college) {
		CollegeExample example = new CollegeExample();
		if(! StringUtils.isEmpty(college) && ! StringUtils.isEmpty(college.getName())) {
			// 查询参数拼装
			Criteria createCriteria 						 = example.createCriteria();
			createCriteria.andNameLike(Symbol.PERCENT+college.getName()+Symbol.PERCENT);
		}
		
		List<College> allColleges = collegeMapper.selectByExample(example);
		return BaseResponse.success(allColleges);
	}
	
	/*
	 * (non-Javadoc)
	 * @see club.renxl.www.management.school.user.service.ICollege#getCollegeDetailInfo(club.renxl.www.management.school.user.dao.domain.College)
	 */
	@Override
	public BaseResponse getCollegeDetailInfo(College college) {
		// 前置校验
		if(StringUtils.isEmpty(college) || StringUtils.isEmpty(college.getCoid())) {
			return BaseResponse.argsError("请选择一个大学...");
		}
		// 业务处理
		List<College> colleges =  collegeMapper.selectCollegeWithProvinceInfoByCollegeCondition(college);
		return BaseResponse.success(colleges);
	}

}
