package com.pdstars.web.controller.log;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pdstars.manager.base.vo.GeneralResVO;
import com.pdstars.manager.base.vo.GeneralResult;
import com.pdstars.manager.common.enums.RestErrorEnum;
import com.pdstars.manager.common.enums.ResultCodeEnum;
import com.pdstars.manager.log.LogService;
import com.pdstars.manager.log.vo.AddEditLogReqVO;
import com.pdstars.manager.log.vo.GetDelLogReqVO;
import com.pdstars.manager.test.TestService;

import io.swagger.annotations.Api;

@Api(value = "日志管理接口", tags = { "日志管理接口" })
@RestController
public class LogController {

	@Autowired
	private GeneralResVO generalResVO;

	@Autowired
	LogService logService;
	
	@Autowired
	TestService testService;

	@RequestMapping(value = "/getLog")
	public @ResponseBody GeneralResVO getLog(@Validated GetDelLogReqVO logReqVO, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException {

		GeneralResVO result = logService.getLogList(logReqVO, request);
		return result;
	}

	@RequestMapping(value = "/addLog")
	public @ResponseBody GeneralResVO addLog(@Validated AddEditLogReqVO logReqVO, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException {

		GeneralResVO result = logService.addLog(logReqVO, request);
		return result;
	}
	
	@RequestMapping(value = "/testController")
	public @ResponseBody GeneralResVO testController(@Validated GetDelLogReqVO logReqVO, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException {
		String result = testService.getTest();
		GeneralResVO rightResult = generalResVO.returnSuccessResult(result);
		return rightResult;
	}
	
	@RequestMapping(value = "/testController8")
	public @ResponseBody GeneralResVO testController2(@Validated GetDelLogReqVO logReqVO, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException {
		String result = testService.getTest() + "321";
		GeneralResVO rightResult = generalResVO.returnSuccessResult(result);
		return rightResult;
	}

}
