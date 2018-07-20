package com.alipay.config;

public enum AlipayAnsyncKeyEnum {
    notify_user_id("user_id"),/*增加用户ID*/
    notify_time("notify_time"), 
    notify_type ("notify_type"), 
    notify_id("notify_id"), 
    sign_type("sign_type"), 
    sign("sign"),
    //上面是通知参数,下面是业务参数key
    out_trade_no("out_trade_no"),
    subject("subject"),
    payment_type("payment_type"),
    trade_no("trade_no"),
    trade_status("trade_status"),
    seller_id("seller_id"),
    seller_email("seller_email"),
    buyer_id("buyer_id"),
    buyer_email("buyer_email"),
    total_fee("total_fee"),
    quantity("quantity"),
    price("price"),
    body("body"),
    gmt_create("gmt_create"),
    gmt_payment("gmt_payment"),
    is_total_fee_adjust("is_total_fee_adjust"),
    use_coupon("use_coupon"),
    discount("discount"),
    refund_status("refund_status"),
    gmt_refund("gmt_refund");

    private final String key;
    AlipayAnsyncKeyEnum(String key){
        this.key=key;
    }
    
    public String getKey() {
        return key;
    }

    public static AlipayAnsyncKeyEnum getByKey(String key){
        for (AlipayAnsyncKeyEnum resultCode : AlipayAnsyncKeyEnum.values()) {
            if (resultCode.getKey().equals(key)) {
                return resultCode;
            }
        }
        return null;
    }
}