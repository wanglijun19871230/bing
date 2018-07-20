package com.pdstars.manager.base.vo;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pdstars.common.util.I18nUtil;
import com.pdstars.manager.common.enums.ResultCodeEnum;

@Component
public class GeneralResVO<T> implements Serializable {
	@Resource
	private I18nUtil i18nUtil;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3737235852895882876L;

	public int resultCode;
	public String msg;
	public T result;

	public GeneralResVO() {
		msg = "";
		result = null;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	/**
	 * 返回成功的操作信息，操作结果为空
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2017年11月3日 下午4:47:48
	 * @param baseMessage
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public GeneralResVO returnSuccessResult(BaseMessage baseMessage)
			throws InstantiationException, IllegalAccessException {

		GeneralResVO res = new GeneralResVO();
		res.setResultCode(ResultCodeEnum.SUCCESS.getCode());
		if (baseMessage != null) {
			String msg = i18nUtil.getMessage(baseMessage.getKey(), baseMessage.getMessage());
			res.setMsg(msg);
		} else {
			res.setMsg("success");
		}
		res.setResult(null);
		return res;
	}

	/**
	 * 返回成功的操作信息，自定义信息
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2018年2月26日 下午4:18:49
	 * @param message
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public GeneralResVO returnSuccessResult(String message) throws InstantiationException, IllegalAccessException {

		GeneralResVO res = new GeneralResVO();
		res.setResultCode(ResultCodeEnum.SUCCESS.getCode());
		res.setMsg(message);
		res.setResult(null);
		return res;
	}

	/**
	 * 返回成功的操作结果
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2017年11月3日 下午4:48:20
	 * @param result
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public GeneralResVO returnSuccessResult(Object result) throws InstantiationException, IllegalAccessException {

		GeneralResVO res = new GeneralResVO();
		res.setResultCode(ResultCodeEnum.SUCCESS.getCode());
		res.setMsg("success");
		res.setResult(result);
		return res;
	}

	/**
	 * 返回失败的类型代码，失败信息，结果集
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2017年11月3日 下午4:48:33
	 * @param resultCode
	 * @param baseMessage
	 * @param result
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public GeneralResVO returnErrorResult(ResultCodeEnum resultCode, BaseMessage baseMessage, Object result)
			throws InstantiationException, IllegalAccessException {
		@SuppressWarnings("unchecked")
		GeneralResVO res = new GeneralResVO();
		res.setResultCode(resultCode.getCode());
		if (baseMessage != null) {
			String msg = i18nUtil.getMessage(baseMessage.getKey(), baseMessage.getMessage());
			res.setMsg(msg);
		}
		res.setResult(result);
		return res;
	}

	@SuppressWarnings("unchecked")
	public Object returnErrorResult(ResultCodeEnum resultCode, BaseMessage baseMessage, Class clzz)
			throws InstantiationException, IllegalAccessException {
		@SuppressWarnings("unchecked")
		GeneralResVO<String> res = (GeneralResVO<String>) clzz.newInstance();
		res.setResultCode(resultCode.getCode());
		if (baseMessage != null) {
			String msg = i18nUtil.getMessage(baseMessage.getKey(), baseMessage.getMessage());
			res.setMsg(msg);
		}
		return res;
	}

	/**
	 * 返回失败的类型代码，自定义信息，结果集
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2017年11月3日 下午4:49:29
	 * @param resultCode
	 * @param msg
	 * @param result
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public GeneralResVO returnErrorResult(ResultCodeEnum resultCode, String msg, Object result)
			throws InstantiationException, IllegalAccessException {
		@SuppressWarnings("unchecked")
		GeneralResVO res = new GeneralResVO();
		res.setResultCode(resultCode.getCode());
		res.setMsg(msg);
		res.setResult(result);
		return res;
	}
}
