package com.pdstars.manager.common.enums;

import com.pdstars.manager.base.vo.BaseMessage;

public enum RestErrorEnum implements BaseMessage {

	BUSSINESS_ERROR("RestErrorCodeEnum.BussinessError", "Bussiness Error"), SERVICE_CURRENTLY_UNAVAILABLE(
			"RestErrorCodeEnum.ServiceNotAvailable", "Service Not Available"), PARAMETER_FORMAT_ERROR(
					"RestErrorCodeEnum.ParameterformatError", "Parameter Format Error");

	/** 代码 */
	private final String key;
	/** 信息所属的key */
	private final String message;

	RestErrorEnum(String key, String message) {
		this.key = key;
		this.message = message;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
