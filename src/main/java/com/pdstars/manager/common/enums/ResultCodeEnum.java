package com.pdstars.manager.common.enums;

/**
 * 返回结果类型枚举
 * @author Yanfa-0509
 * @date 2017年6月30日 下午2:40:20
 * @Title ResultCodeEnum 	
 * @Description：
 */
public enum ResultCodeEnum {

    SUCCESS(10000),
    SYSTEM_ERROR(10001),
    PARAM_ERROR(10002),
    BUSSINESS_ERROR(10003);
    /** 代码 */
    private final int code;

    ResultCodeEnum(int code) {
        this.code = code;
    }
    

    /**
     * 根据code获取ENUM
     * 
     * @param code
     * @return
     */
    public static ResultCodeEnum getByCode(int code) {
        for (ResultCodeEnum resultCode : ResultCodeEnum.values()) {
            if (resultCode.getCode()==code) {
                return resultCode;
            }
        }

        return null;
    }


	public int getCode() {
		return code;
	}
}
