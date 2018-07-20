package com.pdstars.manager.log;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.pdstars.common.util.AuthorizationUtil;
import com.pdstars.common.util.I18nUtil;
import com.pdstars.common.util.IPUtil;
import com.pdstars.common.util.PDStarsStringUtil;
import com.pdstars.dal.daointerface.LogMapper;
import com.pdstars.dal.daointerface.ServerMapper;
import com.pdstars.dal.dataobject.Log;
import com.pdstars.dal.query.LogQuery;
import com.pdstars.manager.base.vo.GeneralResVO;
import com.pdstars.manager.base.vo.GeneralResult;
import com.pdstars.manager.base.vo.TokenModel;
import com.pdstars.manager.common.enums.RestErrorEnum;
import com.pdstars.manager.common.enums.ResultCodeEnum;
import com.pdstars.manager.log.vo.AddEditLogReqVO;
import com.pdstars.manager.log.vo.GetDelLogReqVO;

import cn.hutool.core.date.DateUtil;

@Service
public class LogServiceImpl implements LogService {
	protected static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

	@Resource
	I18nUtil i18nUtil;

	@Autowired
	private GeneralResVO generalResVO;

	@Autowired
	LogMapper logMapper;

	@Autowired
	ServerMapper serverMapper;

	@Override
	public GeneralResVO getLogList(GetDelLogReqVO logReqVO, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		GeneralResult generalResult = new GeneralResult();

		if (PDStarsStringUtil.isNull(logReqVO.getServerIDs())) {
			GeneralResVO rightResult = generalResVO.returnSuccessResult(generalResult);
			return rightResult;
		}
		// 获取token
		TokenModel tokenModel = AuthorizationUtil.getTokenModelFromAuthorization(request);
		if (tokenModel == null) {
			GeneralResVO errorResult = (GeneralResVO) generalResVO.returnErrorResult(ResultCodeEnum.SYSTEM_ERROR,
					RestErrorEnum.BUSSINESS_ERROR, null);
			return errorResult;
		}
		// 登录者所在单位id
		String companyId = tokenModel.getOrganizationID().toString();
		try {
			Integer page = logReqVO.getPage() == null ? 1 : logReqVO.getPage();
			Integer limit = logReqVO.getLimit() == null ? 15 : logReqVO.getLimit();
			PageHelper.startPage(page, limit);

			String[] serverIDArray = PDStarsStringUtil.splitString(logReqVO.getServerIDs(), ",");
			LogQuery logQuery = new LogQuery();
			logQuery.setCompanyID(Long.parseLong(companyId));
			logQuery.setUserName(logReqVO.getUserName());
			logQuery.setTerminalType(logReqVO.getTerminalType());
			logQuery.setOperateModule(logReqVO.getOperateModule());
			logQuery.setServerIDArray(serverIDArray);
			logQuery.setOperateTimeBegin(logReqVO.getStartDate());
			logQuery.setOperateTimeEnd(logReqVO.getEndDate());

			List<Log> logList = logMapper.selectAll();

			generalResult.setResult(logList);
			GeneralResVO rightResult = generalResVO.returnSuccessResult(generalResult);
			return rightResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("日志获取错误：{}", JSON.toJSONString(e));
			GeneralResVO errorResult = (GeneralResVO) generalResVO.returnErrorResult(ResultCodeEnum.SYSTEM_ERROR,
					RestErrorEnum.BUSSINESS_ERROR, null);
			return errorResult;
		}
	}

	@Override
	public GeneralResVO addLog(AddEditLogReqVO logReqVO, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		/*
		 * 如果传入的服务ID不在数据库中的话，则认为该服务没有权限接入日志服务
		 */
		Long organizationID = null;
		String organizationName = "";
		Integer userID = null;
		String userName = "";

		// 获取token
		TokenModel tokenModel = AuthorizationUtil.getTokenModelFromAuthorization(request);
		if (tokenModel != null) {
			organizationID = tokenModel.getOrganizationID();
			organizationName = tokenModel.getOrganization();
			userID = tokenModel.getUserID();
			userName = tokenModel.getUserName();
		}
		try {
			Log log = new Log();
			log.setId(UUID.randomUUID().toString());
			log.setCompanyId(organizationID);
			log.setCompanyName(organizationName);
			log.setUserId(userID);
			log.setUserName(userName);
			String ip = IPUtil.getIpAddrs(getRequest());
			log.setIp(ip);
			log.setTerminalType(logReqVO.getTerminalType());
			log.setLogType("0");
			log.setOperateModule(logReqVO.getOperateModule());
			log.setOperateDesc(logReqVO.getOperateDesc());
			log.setOperateTime(DateUtil.parse(logReqVO.getOperateTime(), "yyyy-MM-dd HH:mm:ss"));
			log.setServerId(logReqVO.getServerID());
			int result = logMapper.insert(log);
			GeneralResVO rightResult = generalResVO.returnSuccessResult(result);
			return rightResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("日志新增错误：{}", JSON.toJSONString(e));
			GeneralResVO errorResult = (GeneralResVO) generalResVO.returnErrorResult(ResultCodeEnum.SYSTEM_ERROR,
					RestErrorEnum.BUSSINESS_ERROR, null);
			return errorResult;
		}
	}

	private static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}
}
