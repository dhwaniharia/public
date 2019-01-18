package com.example.dummyfx.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Order {
	
	/**
	 * ORDER_TYPE
	 */
	public static final String ORDER_TYPE_BUY = "ASK";
	public static final String ORDER_TYPE_SELL = "BID";
	
	/**
	 * CURRENCY
	 */
	public static final String CURRENCY_USD = "USD";
	public static final String CURRENCY_GBP = "GBP";
	
	private String orderId = null;
	@NotNull
	@Size(min=1, message="UserId cannot be empty")
	private String userId = null;
	private String orderType = ORDER_TYPE_BUY;
	
	@NotNull
	@Size(min=1, message="Currency cannot be empty")
	private String fromCurrency = null;

	@NotNull
	@Size(min=1, message="Currency cannot be empty")
	private String toCurrency = null;
	private double price = 0.0;
	@Positive(message="Please enter valid amount")
	private double amount = 0.0;
	private boolean archive = false;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getFromCurrency() {
		return fromCurrency;
	}
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	public String getToCurrency() {
		return toCurrency;
	}
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	
}
