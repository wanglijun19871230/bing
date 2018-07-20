package com.pdstars.manager.log.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

public class AddEditLogReqVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1514541038372066365L;

	/**
	 * 操作平台 0:WEB 1:APP 2:其他
	 */
	private String terminalType;

	/**
	 * 操作模块 0：系统 1：任务
	 */
	private String operateModule;

	/**
	 * 操作说明
	 */
	private String operateDesc;

	/**
	 * 操作时间
	 */
	@NotBlank
	private String operateTime;

	/**
	 * 所属服务ID
	 */
	@NotBlank
	private String serverID;

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

	public String getOperateDesc() {
		return operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getServerID() {
		return serverID;
	}

	public void setServerID(String serverID) {
		this.serverID = serverID;
	}

}
