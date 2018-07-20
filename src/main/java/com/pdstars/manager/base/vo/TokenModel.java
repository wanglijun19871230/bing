package com.pdstars.manager.base.vo;

/**
 * Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 * 
 * @author ScienJus
 * @date 2015/7/31.
 */
public class TokenModel {

	// 用户id
	private Integer userID;

	// 用户名称
	private String userName;

	// 所属单位ID
	private Long organizationID;

	// 所属单位名称
	private String organization;

	// 所属用户组集合
	private String userGroupStr;

	// 是否管理员
	private Integer isManager;

	// 登录类型
	private Integer loginType;

	// 随机生成的uuid
	private String token;

	public Long getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(Long organizationID) {
		this.organizationID = organizationID;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getUserGroupStr() {
		return userGroupStr;
	}

	public void setUserGroupStr(String userGroupStr) {
		this.userGroupStr = userGroupStr;
	}

	public Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
