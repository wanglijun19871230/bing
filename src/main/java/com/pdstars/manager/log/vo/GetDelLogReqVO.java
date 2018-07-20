package com.pdstars.manager.log.vo;

import java.io.Serializable;

import com.pdstars.manager.base.vo.GeneralReqVO;

public class GetDelLogReqVO extends GeneralReqVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1514541038372066365L;
	/**
	 * 列表起始时间
	 */
	private String startDate;

	/**
	 * 列表结束时间
	 */
	private String endDate;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 操作平台 0:WEB 1:APP 2:其他
	 */
	private String terminalType;

	/**
	 * 操作模块 0：系统 1：任务  2：档案
	 */
	private String operateModule;

	/**
	 * 所属服务ID集合，多个值以逗号隔开
	 */
	private String serverIDs;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getOperateModule() {
		return operateModule;
	}

	public void setOperateModule(String operateModule) {
		this.operateModule = operateModule;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServerIDs() {
		return serverIDs;
	}

	public void setServerIDs(String serverIDs) {
		this.serverIDs = serverIDs;
	}

}
