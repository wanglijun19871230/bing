/**
 *  OpenPlatformUtils.java
 *  OpenPlatformUtils
 *  desc:
 *  
 *  
 *  @Auth Tony Liu 
 *  @Version  2015年6月30日-上午9:38:53
 */
package com.pdstars.manager.encrypt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.Assert;


public class OpenUtils {
	
	
	/** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
    public static String signWithSHA1(String toSign) {
    		
    	return DigestUtils.shaHex(toSign);
    }
    
    
    public static HashMap<String, String> parseRequestParamMap(HttpServletRequest request){
    	
    	Assert.notNull(request);
    	
    	 //获取支付宝POST过来的异步通知参数
    	HashMap<String,String> map = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        //Assert.notEmpty(requestParams);
        if(requestParams==null){
        	return map;
        }
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            map.put(name, valueStr);
        }
        return map;
    	
    }
    
}
