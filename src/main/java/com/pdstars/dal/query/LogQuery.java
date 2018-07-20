package com.pdstars.dal.query;

import java.io.Serializable;

public class LogQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5999759808107292488L;

	private long companyID;

	private String userName;

	private String terminalType;

	private String operateModule;

	private String operateTimeBegin;

	private String operateTimeEnd;

	private String[] serverIDArray;

	public LogQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCompanyID() {
		return companyID;
	}

	public void setCompanyID(long companyID) {
		this.companyID = companyID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getOperateTimeBegin() {
		return operateTimeBegin;
	}

	public void setOperateTimeBegin(String operateTimeBegin) {
		this.operateTimeBegin = operateTimeBegin;
	}

	public String getOperateTimeEnd() {
		return operateTimeEnd;
	}

	public void setOperateTimeEnd(String operateTimeEnd) {
		this.operateTimeEnd = operateTimeEnd;
	}

	public String[] getServerIDArray() {
		return serverIDArray;
	}

	public void setServerIDArray(String[] serverIDArray) {
		this.serverIDArray = serverIDArray;
	}	

}
