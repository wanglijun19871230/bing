package com.pdstars.common.util;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.pdstars.manager.base.vo.TokenModel;
import com.pdstars.manager.common.enums.Constants;
import com.pdstars.manager.encrypt.DES;

/**
 * 用户权限工具类
 * 
 * @author Yanfa-0509
 * @date 2017年7月21日 上午11:31:14
 * @Title AuthorizationUtil @Description：
 */
@Component
public class AuthorizationUtil {

	protected static final Logger logger = LoggerFactory.getLogger(AuthorizationUtil.class);

	@Value("${crypto.token.key}")
	String TOKEN_DES_KEY;

	private static AuthorizationUtil authorizationUtil;

	@PostConstruct
	// @PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行
	public void init() {
		authorizationUtil = this;
	}

	/**
	 * 从requst中获取Token对象
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2018年2月27日 下午4:32:29
	 * @param req
	 * @return
	 */
	public static TokenModel getTokenModelFromAuthorization(HttpServletRequest req) {
		TokenModel tokenModel = null;
		try {
			String authorization = req.getHeader(Constants.AUTHORIZATION);
			byte[] authorizationByte = Base64.decodeBase64(authorization);
			authorization = new String(authorizationByte);
			authorization = DES.decrypt(authorization, authorizationUtil.TOKEN_DES_KEY);
			tokenModel = JSON.parseObject(authorization, TokenModel.class);
		} catch (Exception e) {
			logger.error("Token加密解密失败{}", JSON.toJSONString(e));
		}

		return tokenModel;
	}

	/**
	 * 从request中获取用户ID
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2017年7月20日 下午3:49:43
	 * @param req
	 * @return
	 */
	public static Integer getUserIDFromAuthorization(HttpServletRequest req) {
		TokenModel tokenModel = null;
		try {
			String authorization = req.getHeader(Constants.AUTHORIZATION);
			byte[] authorizationByte = Base64.decodeBase64(authorization);
			authorization = new String(authorizationByte);
			authorization = DES.decrypt(authorization, authorizationUtil.TOKEN_DES_KEY);
			tokenModel = JSON.parseObject(authorization, TokenModel.class);
		} catch (Exception e) {
			logger.error("Token加密解密失败{}", JSON.toJSONString(e));
		}

		if (tokenModel != null) {
			return tokenModel.getUserID();
		} else {
			return null;
		}

	}
}
