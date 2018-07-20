package com.alipay.config;

public enum AlipayClientErrorCode {
    
    FAILED(4000, "订单支付失败"),
    SUCCESS(9000, "订单支付成功"), 
    PROCESSING(8000, "正在处理中"), 
    USER_CANCLED(6001, "用户中途取消"), 
    NET_ERROR(6002, "网络连接出错");
    
    private final int key;
    private final String message;
    
    AlipayClientErrorCode(int key, String message){
        this.key=key;
        this.message=message;
    }
    
    public int getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }
    
    public static AlipayClientErrorCode getByKey(int key){
        for (AlipayClientErrorCode resultCode : AlipayClientErrorCode.values()) {
            if (resultCode.getKey()==key) {
                return resultCode;
            }
        }
        return null;
    }
    public static AlipayClientErrorCode getByMessage(String message){
        for (AlipayClientErrorCode resultCode : AlipayClientErrorCode.values()) {
            if (resultCode.getMessage().equalsIgnoreCase(message)) {
                return resultCode;
            }
        }
        return null;
    }
}