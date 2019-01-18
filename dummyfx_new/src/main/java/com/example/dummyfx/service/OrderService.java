package com.example.dummyfx.service;

import java.util.List;

import com.example.dummyfx.model.Order;

public interface OrderService {

	public boolean registerOrder(Order order);
	
	public boolean cancelOrder(String orderId);
	
	public List<Order> getAllOrders();
	
	public List<Order> getMatchedOrders();
	
	public List<Order> getUnmatchedOrders();
}
