package com.pdstars.manager.interceptor;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import DBDES.DBDES;

import com.alibaba.fastjson.JSON;

@Intercepts({
		@Signature(type = Executor.class, method = "update", args = {
				MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = {
				MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class }) })
@Component
public class DBInterceptor implements Interceptor {

	@Value("${regexp.String}")
	String REGEXP_STRING;

	@Value("${notdes.String}")
	String NOTDES_STRING;

	@Value("${server.company}")
	String KEY_STRING;

	protected static final Logger logger = LoggerFactory
			.getLogger(DBInterceptor.class);

	/**
	 * 获取参数值(用于打印SQL)
	 * 
	 * @author:Wanglijun
	 * @DateTime:2016年12月12日 上午10:04:35
	 * @param obj
	 * @return
	 */
	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(
					DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}
		}
		return value;
	}

	/**
	 * 解析SQL语句(用于打印SQL)
	 * 
	 * @author:Wanglijun
	 * @DateTime:2016年12月12日 上午10:04:50
	 * @param configuration
	 * @param boundSql
	 * @return
	 */
	private static String parseSql(Configuration configuration,
			BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?",
						getParameterValue(parameterObject));

			} else {
				MetaObject metaObject = configuration
						.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql
								.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					}
				}
			}
		}
		return sql;
	}

	/**
	 * 校验字符串是否需要加密
	 * 
	 * @author:Wanglijun
	 * @DateTime:2016年12月9日 下午4:51:05
	 * @param valStr
	 * @return
	 */
	private Boolean isDesString(String valStr) {

		if (valStr == null) {
			return false;
		}

		String[] regexpStrings = REGEXP_STRING.split("SEP");
		String[] notdesStrings = NOTDES_STRING.split("SEP");

		/* 是否符合正则表达式 */
		for (String regeString : regexpStrings) {
			Pattern pattern = Pattern.compile(regeString);
			Matcher matcher = pattern.matcher(valStr);
			if (matcher.matches()) {
				return false;
			}
		}

		/* 是否符合无需加密字符串 */
		for (String notdesString : notdesStrings) {
			if (valStr.equalsIgnoreCase(notdesString)){
				return false;
			}
				
		}

		if (valStr.equals("-1") == true) {
			return false;
		}
		return true;
	}

	/**
	 * 加密解密Object类型参数
	 * 
	 * @author:Wanglijun
	 * @DateTime:2016年12月9日 下午4:49:59
	 * @param parameter
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private Object desObjectPara(Object parameter)
			throws IllegalArgumentException, IllegalAccessException {
		/* 反射当前类 */
		Class cls = parameter.getClass();
		String valStr = null;
		Field[] fields = cls.getDeclaredFields();

		/* 反射父类 */
		Class superClass = cls.getSuperclass();
		Field[] fieldsSuper = superClass.getDeclaredFields();

		if (cls == String[].class) {
			int length = Array.getLength(parameter);
			for (int i = 0; i < length; i++) {
				String a = Array.get(parameter, i).toString();
				if (isDesString(a) == false) {
					continue;
				}
				Array.set(parameter, i, DBDES.DESString(a, KEY_STRING));
			}
		}

		if (cls == String.class) {
			if (isDesString(parameter.toString()) == true) {
				parameter = DBDES.DESString(parameter.toString(), KEY_STRING);
			}
			
			return parameter;
		}

		/* 加密当前类中的参数 */
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			f.setAccessible(true);
			if (f.get(parameter) == null) {
				valStr = null;
				continue;
			} else if (f.get(parameter).getClass() == String[].class) {
				int length = Array.getLength(f.get(parameter));
				for (int j = 0; j < length; j++) {
					String a = Array.get(f.get(parameter), j).toString();
					if (isDesString(a) == false) {
						continue;
					}
					Array.set(f.get(parameter), j,
							DBDES.DESString(a, KEY_STRING));
				}
			} else {
				valStr = f.get(parameter).toString();
			}
			// 校验字符串是否需要加密
			if (isDesString(valStr) == false) {
				continue;
			}

			// 将(xx,xx,xx)此类格式中的逗号进行加密，方便后期处理
			valStr = valStr.replace(",", DBDES.DESString(",", KEY_STRING));

			/* 只加密字符串 */
			if (f.getType().getName() == String.class.getName()) {
				f.set(parameter, DBDES.DESString(valStr, KEY_STRING));
			}

			/* 加密List<String>中的元素 */
			if (f.getType().getName() == List.class.getName()) {
				List<Object> list = (ArrayList<Object>) f.get(parameter);
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).getClass() == String.class) {
						if (isDesString(list.get(j).toString()) == false) {
							continue;
						}
						list.set(j, DBDES.DESString(list.get(j).toString(),
								KEY_STRING));
					}
				}
			}
		}

		/* 加密父类中的参数 */
		for (int i = 0; i < fieldsSuper.length; i++) {
			Field f = fieldsSuper[i];
			f.setAccessible(true);
			if (f.get(parameter) == null) {
				valStr = null;
				continue;
			} else {
				valStr = f.get(parameter).toString();
			}

			valStr = valStr.replace(",", DBDES.DESString(",", KEY_STRING));

			if (isDesString(valStr) == false) {
				continue;
			}
			/* 只加密字符串 */
			if (f.getType().getName() == String.class.getName()) {
				f.set(parameter, DBDES.DESString(valStr, KEY_STRING));
			}

			/* 加密List<String>中的元素 */
			if (f.getType().getName() == List.class.getName()) {
				List<Object> list = (ArrayList<Object>) f.get(parameter);
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j).getClass() == String.class) {
						if (isDesString(list.get(j).toString()) == false) {
							continue;
						}
						list.set(j, DBDES.DESString(list.get(j).toString(),
								KEY_STRING));
					}

				}
			}
		}
		return parameter;
	}

	/**
	 * 加密解密HashMap类型的参数
	 * 
	 * @author:Wanglijiun
	 * @DateTime:2016年12月12日 上午9:59:01
	 * @param paramMap
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private void desHashMapPara(HashMap<String, Object> paramMap)
			throws IllegalArgumentException, IllegalAccessException {
		Iterator iter = paramMap.entrySet().iterator();
		String patternRule = "param[1-9]\\d*";
		while (iter.hasNext()) {// 循环取值
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			if ((key == null) || val == null) {
				continue;
			}

			/* 如果参数是以param+整数的形式存在，则不进行加密，避免重复加密 */
			Pattern pattern = Pattern.compile(patternRule);
			Matcher matcher = pattern.matcher(key.toString());
			if (matcher.matches()) {
				continue;
			}

			if (val instanceof String) {// 如果HashMap中的值是以字符串形式存在
				// 如果字符串是以','隔开的格式化，则先将','加密
				String valStr = val.toString();
				/* 如果字符串是以','隔开的格式化，则先将','加密 */
				valStr = valStr.replace(",", DBDES.DESString(",", KEY_STRING));

				if (isDesString(valStr) == false) {
					continue;
				}
				paramMap.put(key.toString(),
						DBDES.DESString(val.toString(), KEY_STRING));
			} else if (val instanceof Integer) {
				continue;
			} else if (val instanceof Serializable) {// 如果参数类似于Query类形式存在的话
				desObjectPara(val);
				paramMap.put(key.toString(), val);
				continue;
			}
		}

	}

	/**
	 * 加密BoundSql里的参数
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2016年12月28日 下午4:00:37
	 * @param parameterObject
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private Object desBoundSqlPara(Object boundSqlPara, Object invocationPara)
			throws IllegalArgumentException, IllegalAccessException {
		if (boundSqlPara != null) {
			if (boundSqlPara instanceof HashMap) {
				HashMap<String, Object> paramMap = (HashMap) boundSqlPara;
				try {
					desHashMapPara(paramMap);
				}

				catch (Exception ex) {
					logger.error("数据库加密、解密失败{}", JSON.toJSONString(ex));
					return invocationPara;
				}

			} else if (boundSqlPara instanceof Serializable) {
				invocationPara = desObjectPara(invocationPara);
				boundSqlPara = invocationPara;
				return invocationPara;
			}
		}
		return invocationPara;

	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// return null;
		// TODO Auto-generated method stub

		MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
		Configuration configuration = statement.getConfiguration();
		Object parameter = invocation.getArgs()[1];
		BoundSql boundSql = statement.getBoundSql(parameter);
		String sqlOrig = "";
		try {
			sqlOrig = parseSql(configuration, boundSql);
			logger.info("Original SQL:--- {}", sqlOrig);
		} catch (Exception e) {

		}
		if (boundSql.getSql().contains("LAST_INSERT_ID()") == false) {
			desBoundSqlPara(boundSql.getParameterObject(), parameter);
		}

		long timeAfterDes = System.currentTimeMillis();// 加密语句结束时间
		Configuration configurationDes = statement.getConfiguration();
		BoundSql boundSqlDes = statement.getBoundSql(parameter);
		try {
			String sqlFinal = parseSql(configurationDes, boundSqlDes);
			logger.info("AfterDes SQL:--- {}", sqlFinal);
		} catch (Exception e) {

		}
				
		Object returnValue = null;
		returnValue = invocation.proceed();// 执行SQL并获取执行结果

		// 执行完SQL之后，将SQL语句中的参数进行解密，以便后续使用
		desBoundSqlPara(boundSql.getParameterObject(), parameter);
		long timeGetReturn = System.currentTimeMillis();// 收到返回数据事件

		if (boundSql.getSql().contains("LAST_INSERT_ID()") == true) {
			return returnValue;
		}

		if (returnValue instanceof ArrayList<?>) {
			List<Object> list = (ArrayList<Object>) returnValue;

			for (int i = 0; i < list.size(); i++) {
				list.set(i, desObjectPara(list.get(i)));
			}
		}

		logger.info("GetReturnTime:--- {}", (timeGetReturn - timeAfterDes)
				/ 1000f + "秒");
		return returnValue;
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}

}
