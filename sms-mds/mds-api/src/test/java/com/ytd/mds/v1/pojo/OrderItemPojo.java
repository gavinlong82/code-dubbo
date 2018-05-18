package com.ytd.mds.v1.pojo;

import java.io.Serializable;

public class OrderItemPojo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderNo;
	private String productName;
	private String productType;
	private Long price;
	private Long count;

	@Override
	public String toString() {
		return String.format("orderNo:%s,productName:%s,productType:%s,price:%s,count:%s",
				new Object[] { orderNo, productName, productType, price, count });
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
