package com.ytd.mds.v1.pojo;

import java.io.Serializable;

public class UserPojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String userName;
	private String loginName;
	private String address;
	private String telephone;
	private String company;
	private String sex;
	private Integer age;
	private String email;

	@Override
	public String toString() {
		return String.format(
				"userId:%s,userName:%s,loginName:%s,address:%s,telephone:%s,company:%s,sex:%s,age:%s,email:%s",
				new Object[] { userId, userName, loginName, address, telephone, company, sex, age, email });
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
