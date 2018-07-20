package com.pdstars.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BeanUtil {

	protected static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);
    /** 
     * 根据属性名获取属性值 
     * */  
       public static Object getFieldValueByName(String fieldName, Object o) {  
           try {    
               String firstLetter = fieldName.substring(0, 1).toUpperCase();    
               String getter = "get" + firstLetter + fieldName.substring(1);    
               Method method = o.getClass().getMethod(getter, new Class[] {});    
               Object value = method.invoke(o, new Object[] {});    
               return value;    
           } catch (Exception e) {    
        	   logger.error(e.getMessage(),e);    
               return null;    
           }    
       }   
         
       public static String getFieldTypeByName(String fieldName, Object o){
    	   String type = null;
    	   try {
			Field field = o.getClass().getDeclaredField(fieldName);
			type = field.getType().getName();
		} catch (NoSuchFieldException e) {
			try {
				//从父类中获取字段
				Field field = o.getClass().getSuperclass().getDeclaredField(fieldName);
				type = field.getType().getName();
			} catch (NoSuchFieldException e1) {
				 logger.error(e.getMessage(),e);    
	             return type; 
			} catch (SecurityException e1) {
				 logger.error(e.getMessage(),e);    
	             return type;    
			}
			   
		} catch (SecurityException e) {
			 logger.error(e.getMessage(),e);    
             return type;    
		}
    	   return type;
       }
       
       /** 
        * 获取属性名数组 （不包含父类字段）
        * */  
       public static String[] getFiledName(Object o){  
    	   //不包含父类字段
        Field[] fields = o.getClass().getDeclaredFields();  
            String[] fieldNames=new String[fields.length];  
        for(int i=0;i<fields.length;i++){  
            System.out.println(fields[i].getType());  
            fieldNames[i]=fields[i].getName();  
        }  
        return fieldNames;  
       }  
         
       /** 
        * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list 
        * */  
       public static List getFiledsInfo(Object o){  
        Field[] fields=o.getClass().getDeclaredFields();  
            String[] fieldNames=new String[fields.length];  
            List list = new ArrayList();  
            Map infoMap=null;  
        for(int i=0;i<fields.length;i++){  
            infoMap = new HashMap();  
            infoMap.put("type", fields[i].getType().toString());  
            infoMap.put("name", fields[i].getName());  
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));  
            list.add(infoMap);  
        }  
        return list;  
       }  
         
       /** 
        * 获取对象的所有属性值，返回一个对象数组 
        * */  
       public static Object[] getFiledValues(Object o){  
        String[] fieldNames=getFiledName(o);  
        Object[] value=new Object[fieldNames.length];  
        for(int i=0;i<fieldNames.length;i++){  
            value[i]=getFieldValueByName(fieldNames[i], o);  
        }  
        return value;  
       }      
}
