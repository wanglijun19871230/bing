/**
 * 
 */
package com.pdstars.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * 获取本地IP和机器名称
 * </p>
 * 
 * @author Frank.fan
 * @version $Id: IPUtil.java, v 0.1 2011-3-16 下午04:44:37 fanmanrong Exp $
 */
public class IPUtil {

    public static String getIP() {
        try {
            InetAddress thisIp = InetAddress.getLocalHost();
            return thisIp.getHostAddress();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = getIpAddrs(request);
        if (StringUtils.isNotBlank(ip)) {
            if (ip.indexOf(",") != -1) {
                String[] ips = ip.split(",");
                for (String item : ips) {
                    if (item != null && !item.equalsIgnoreCase("unknown")) {
                        return item.trim();
                    }
                }
            }
        }
        return ip;
    }

    public static String getIpAddrs(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // System.out.println("当前请求的IP地址集:"+ip);
        return ip;
    }

    public static String getRemotePort(HttpServletRequest request) {
        System.out.println(" remote info: ip["+request.getRemoteAddr()+":"+request.getRemotePort()+"] host["+request.getRemoteHost()+"]" );
        
        return String.valueOf(request.getRemotePort());
    }
    
    public static String getMachineName() {
        try {
            InetAddress thisIp = InetAddress.getLocalHost();
            return thisIp.getHostName();
        } catch (Exception e) {
            return "";
        }
    }

    public static Locale getLocale(HttpServletRequest request) {
        String lang = request.getHeader("Accept-Language");
        Locale locale = Locale.getDefault();
        try {
            if (StringUtils.isNotBlank(lang)) {
                if (lang.indexOf("_") != -1) {
                    String[] ll = lang.split("_");
                    if (ll.length == 2)
                        locale = new Locale(ll[0], ll[1]);
                } else {
                    locale = new Locale(lang);
                }
            }
        } catch (Exception e) {

        }
        return locale;
    }
    
    /**
     * 根据ip获取行政单位
     * @author:wsh
     * @DateTime:2017年1月17日 上午11:28:13
     * @param params
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String getAddress(String params, String encoding) throws Exception{  
        String path = "http://ip.taobao.com/service/getIpInfo.php";  
        String returnStr = getRs(path, params, encoding);  
        JSONObject json=null;  
        if(returnStr != null){  
            json = JSON.parseObject(returnStr);  
            if("0".equals(json.get("code").toString())){ 
                String archive=json.get("data").toString();  
                return archive;
            }else{  
                return "获取地址失败";  
            }  
        }  
        return null;  
    }
    
    public static String getRs(String path, String params, String encoding){  
        URL url = null;  
        HttpURLConnection connection = null;  
        try {  
            url = new URL(path);  
            connection = (HttpURLConnection)url.openConnection();// 新建连接实例  
            connection.setConnectTimeout(10000);// 设置连接超时时间，单位毫  
            connection.setReadTimeout(10000);// 设置读取数据超时时间，单位毫
            connection.setDoInput(true);// 是否打开输出 true|false  
            connection.setDoOutput(true);// 是否打开输入流true|false  
            connection.setRequestMethod("POST");// 提交方法POST|GET  
            connection.setUseCaches(false);// 是否缓存true|false  
            connection.connect();// 打开连接端口  
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());  
            out.writeBytes(params);  
            out.flush();  
            out.close();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),encoding));  
            StringBuffer buffer = new StringBuffer();  
            String line = "";  
            while ((line = reader.readLine())!= null) {  
                buffer.append(line);  
            }  
            reader.close();  
            return buffer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            connection.disconnect();// 关闭连接  
        }  
        return null;  
    }  
}
