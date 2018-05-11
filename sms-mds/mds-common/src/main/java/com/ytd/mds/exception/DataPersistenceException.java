package com.ytd.mds.exception;

/**
 * @description 数据持久化自定义异常类
 * @project: mds-common
 * @Date:2018年4月13日
 * @version 1.0
 * @Company: YTD
 * @author gavinlong
 */
public class DataPersistenceException extends CustomRuntimeException {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public DataPersistenceException() {
		super();
	}

	public DataPersistenceException(String message) {
		super(message);
	}

	public DataPersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataPersistenceException(Throwable cause) {
		super(cause);
	}
}
