package club.renxl.www.management.school.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import club.renxl.www.enums.UserResponse;
import club.renxl.www.management.school.user.dao.AreaMapper;
import club.renxl.www.management.school.user.dao.domain.Area;
import club.renxl.www.management.school.user.dao.domain.AreaExample;
import club.renxl.www.management.school.user.dao.domain.AreaExample.Criteria;
import club.renxl.www.management.school.user.service.IArea;
import club.renxl.www.response.BaseResponse;

@Service
public class IAreaImpl implements IArea {
	
	@Autowired
	private AreaMapper areaMapper ;
	
	@Override
	public BaseResponse lookAll(Area area) {
		// 前置校验
		if(StringUtils.isEmpty(area)) {
			area = new Area();
		}
		// 获取字典数据
		AreaExample example    = initSqlConditionByAreaCondition(area);
		List<Area> allProvinces = areaMapper.selectByExample(example );
		
		// 返回框架结果
		if(CollectionUtils.isEmpty(allProvinces)) {
			return BaseResponse.error(UserResponse.SELECT_NULL_ERROR.getCode(), "查询信息不存在...");
		}else {
			return BaseResponse.success(allProvinces);
		}
	}
	
	/**
	 * 	查询省区名称为指定名称的省区信息获取全部省区信息
	 * @param area
	 * @return
	 */
	private AreaExample initSqlConditionByAreaCondition(Area area) {
		AreaExample example = new AreaExample();
		if(!StringUtils.isEmpty(area.getAreaname())) {
			Criteria createCriteria = example.createCriteria();
			createCriteria.andAreanameEqualTo(area.getAreaname());
		}
		return example;
	}

}
