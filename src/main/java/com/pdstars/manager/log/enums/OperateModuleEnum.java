package com.pdstars.manager.log.enums;

/**
 * 操作模块枚举
 * @author Yanfa-0509
 * @date 2018年3月1日 上午10:56:52
 * @Title OperateTypeEnum 	
 * @Description：
 */
public enum OperateModuleEnum {

	SYSTEM("system", "0"), 
	DETECT("detect", "1"),
	OHTERS("others", "2");

	/** 账户类型 */
	private final String type;
	/** 代码 */
	private final String code;

	OperateModuleEnum(String type, String code) {
		this.type = type;
		this.code = code;
	}

	/**
	 * 根据type获取ENUM
	 * 
	 * @param type
	 * @return
	 */
	public static OperateModuleEnum getByType(String type) {
		for (OperateModuleEnum resultCode : OperateModuleEnum.values()) {
			if (resultCode.getType().equals(type)) {
				return resultCode;
			}
		}

		return null;
	}

	/**
	 * 根据code获取ENUM
	 * 
	 * @param code
	 * @return
	 */
	public static OperateModuleEnum getByCode(String code) {
		for (OperateModuleEnum resultCode : OperateModuleEnum.values()) {
			if (resultCode.getCode() == code) {
				return resultCode;
			}
		}

		return null;
	}

	public String getType() {
		return type;
	}

	public String getCode() {
		return code;
	}
}
