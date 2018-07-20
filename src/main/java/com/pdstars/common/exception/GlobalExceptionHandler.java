package com.pdstars.common.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.pdstars.manager.base.vo.ArgumentInvalidResult;
import com.pdstars.manager.base.vo.GeneralResVO;
import com.pdstars.manager.common.enums.RestErrorEnum;
import com.pdstars.manager.common.enums.ResultCodeEnum;

@ControllerAdvice
// 如果返回的为json数据或其它对象，添加该注解
@ResponseBody
public class GlobalExceptionHandler {
	protected static final Logger logger = LoggerFactory
			.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private GeneralResVO generalResVO;
	
	// 添加全局异常处理流程，根据需要设置需要处理的异常，本文以MethodArgumentNotValidException为例
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Object MethodArgumentNotValidHandler(HttpServletRequest request,
			MethodArgumentNotValidException exception) throws Exception {
		// 按需重新封装需要返回的错误信息
		List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
		// 解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
			invalidArgument.setMessage(error.getDefaultMessage());
			invalidArgument.setField(error.getField());
			invalidArgument.setRejectedValue(error.getRejectedValue());
			invalidArguments.add(invalidArgument);
		}

		return generalResVO.returnErrorResult(ResultCodeEnum.PARAM_ERROR,
				RestErrorEnum.PARAMETER_FORMAT_ERROR, invalidArguments);
	}

	@ExceptionHandler(value = BindException.class)
	public Object BindHandler(HttpServletRequest request,
			BindException exception) throws Exception {
		// 按需重新封装需要返回的错误信息
		List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
		// 解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
			invalidArgument.setMessage(error.getDefaultMessage());
			invalidArgument.setField(error.getField());
			invalidArgument.setRejectedValue(error.getRejectedValue());
			invalidArguments.add(invalidArgument);
		}

		return generalResVO.returnErrorResult(ResultCodeEnum.PARAM_ERROR,
				RestErrorEnum.PARAMETER_FORMAT_ERROR, invalidArguments);
	}

	@ExceptionHandler(value = RuntimeException.class)
	public Object RuntimeHandler(HttpServletRequest request,
			RuntimeException exception) throws Exception {

		return generalResVO.returnErrorResult(ResultCodeEnum.SYSTEM_ERROR,
				RestErrorEnum.SERVICE_CURRENTLY_UNAVAILABLE, null);
	}
}
