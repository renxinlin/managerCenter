package club.renxl.www.management.school.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import club.renxl.www.response.BaseResponse;



/**
 * 异常处理
 * 使用方式:组件扫描后生效
 * 
 * @author renxl
 * @date 2018/09/26
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
 
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
    
    //TODO 细分不同的异常
    
    /**
     * 全局异常处理
     * @param e
     * @return
     */
    @ResponseBody
	@ExceptionHandler({Exception.class})
	public BaseResponse defaultExceptionHandler(Exception  e){
    	logger.error(" error =           system exception message        ==> "+e.getMessage());
		logger.error(" error  ==          system exception               ==> "+e);
		e.printStackTrace();
		return BaseResponse.error("系统异常,请稍后在试...");
	}
}