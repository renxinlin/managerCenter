package club.renxl.www.management.school.user.http.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import club.renxl.www.management.school.user.service.IArea;
import club.renxl.www.response.BaseResponse;

@RestController
@RequestMapping("area")
public class AreaController {
	
	@Autowired
	private IArea iArea;
	
	@RequestMapping("look-all")
	public BaseResponse lookPrvinces() {
		return iArea.lookAll(null);
	}
}
