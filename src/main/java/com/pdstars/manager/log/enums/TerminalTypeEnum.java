package com.pdstars.manager.log.enums;

/**
 * 操作平台枚举
 * @author Yanfa-0509
 * @date 2018年3月1日 上午10:55:38
 * @Title TerminalTypeEnum 	
 * @Description：
 */
public enum TerminalTypeEnum {

	WEB("web", "0"), 
	APP("app", "1"),
	OHTERS("others", "2");

	/** 账户类型 */
	private final String type;
	/** 代码 */
	private final String code;

	TerminalTypeEnum(String type, String code) {
		this.type = type;
		this.code = code;
	}

	/**
	 * 根据type获取ENUM
	 * 
	 * @param type
	 * @return
	 */
	public static TerminalTypeEnum getByType(String type) {
		for (TerminalTypeEnum resultCode : TerminalTypeEnum.values()) {
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
	public static TerminalTypeEnum getByCode(String code) {
		for (TerminalTypeEnum resultCode : TerminalTypeEnum.values()) {
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
