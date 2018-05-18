package com.ytd.mds.v1.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderPojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderNo;
	private String orderName;
	private Date createTime;
	private List<OrderItemPojo> items;
	private String orderStatus;
	private Long userId;

	@Override
	public String toString() {
		return String.format("orderNo:%s,orderName:%s,createTime:%s,items:{%s},orderStatus:%s,userId:%s",
				new Object[] { orderNo, orderName, createTime, items, orderStatus,userId });
	}
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<OrderItemPojo> getItems() {
		return items;
	}

	public void setItems(List<OrderItemPojo> items) {
		this.items = items;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
