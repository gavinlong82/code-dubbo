package com.ytd.mds.pojo;

import java.util.Date;

import com.ytd.mds.utils.UserStatuEnum;


/**
 * 
 * @description 操作员
 * @project: sms-mis
 * @Date:2017-6-22
 * @version 1.0
 * @Company: YTD
 * @author gavinlong
 */
public class User implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId; // 用户id
	private String loginName; // 用户名
	private String password; // 密码
	private String userName; // 姓名
	private String lastLogin; // 最后登录时间
	private String loginIp; // 用户登录ip地址
	private String telePhone;
	private String email;
	private Date createTime;
	private String roleId;
	private String customerId;
	private UserStatuEnum status;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserStatuEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatuEnum status) {
		this.status = status;
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {

		return "USER_ID:" + userId + ",LOGIN_NAME:" + loginName + ",PASSWORD:" + password + ",USER_NAME:" + userName
				+ ",CUSTOMER_ID:" + customerId + ",ROLE_ID:" + roleId+",STATUS:"+status.getValue();
	}
}
