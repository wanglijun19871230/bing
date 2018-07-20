package com.pdstars.manager.log;

import javax.servlet.http.HttpServletRequest;

import com.pdstars.manager.base.vo.GeneralResVO;
import com.pdstars.manager.base.vo.GeneralResult;
import com.pdstars.manager.log.vo.AddEditLogReqVO;
import com.pdstars.manager.log.vo.GetDelLogReqVO;

public interface LogService {

	/**
	 * 获取日志列表
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2018年2月28日 下午5:48:00
	 * @param logReqVO
	 * @param request
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	GeneralResVO getLogList(GetDelLogReqVO logReqVO, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException;

	/**
	 * 新增日志
	 * 
	 * @author:Yanfa-0509
	 * @DateTime:2018年3月1日 上午10:26:51
	 * @param logReqVO
	 * @param request
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	GeneralResVO addLog(AddEditLogReqVO logReqVO, HttpServletRequest request)
			throws InstantiationException, IllegalAccessException;

}
