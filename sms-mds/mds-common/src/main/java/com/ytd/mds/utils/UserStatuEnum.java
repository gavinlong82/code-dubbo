package com.ytd.mds.utils;

public enum UserStatuEnum {
	ENABLE("0"), DESIBLE("1");
	UserStatuEnum(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
