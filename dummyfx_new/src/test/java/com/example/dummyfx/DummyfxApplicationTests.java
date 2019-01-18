package com.example.dummyfx;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dummyfx.model.Order;
import com.example.dummyfx.service.OrderService;
import com.example.dummyfx.service.OrderServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DummyfxApplicationTests {
	
	@Autowired
	OrderService orderService;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testRegisterOrder()
	{
		Order order = new Order();
		order.setUserId("user1");
		order.setFromCurrency("GBP");
		order.setToCurrency("USD");
		order.setAmount(100);
		order.setOrderType(Order.ORDER_TYPE_BUY);
		boolean success = orderService.registerOrder(order);
		assertEquals(success, true);
	}
	
	@Test
	public void testCancelOrder()
	{
		Order order = prepareData();
		OrderServiceImpl.orderMap.put(order.getOrderId(), order);
		orderService.cancelOrder(order.getOrderId());
		assertEquals(order.isArchive(), true);
	}
	
	@Test
	public void testMatchedOrders()
	{
		List<Order> orders = prepareOrderList();
		for (Order order : orders) {
			OrderServiceImpl.orderMap.put(order.getOrderId(), order);
		}
		List<Order> matchedOrders = orderService.getMatchedOrders();
		assertEquals(matchedOrders.size(), 4);
		
	}
	
	@Test
	public void testUnMatchedOrders()
	{
		List<Order> orders = prepareOrderList();
		for (Order order : orders) {
			OrderServiceImpl.orderMap.put(order.getOrderId(), order);
		}
		List<Order> matchedOrders = orderService.getUnmatchedOrders();
		assertEquals(matchedOrders.size(), 2);
	}
	
	private Order prepareData()
	{
		Order order = new Order();
		order.setUserId("user1");
		order.setFromCurrency("GBP");
		order.setToCurrency("USD");
		order.setAmount(100);
		order.setOrderType(Order.ORDER_TYPE_BUY);
		order.setOrderId("1");
		return order;
	}
	
	private List<Order> prepareOrderList()
	{
		List<Order> orderList = new ArrayList<Order>();
		Order order = new Order();
		order.setUserId("user1");
		order.setFromCurrency("GBP");
		order.setToCurrency("USD");
		order.setAmount(100);
		order.setOrderType(Order.ORDER_TYPE_BUY);
		order.setOrderId("1");
		orderList.add(order);
		
		Order order2 = new Order();
		order2.setUserId("user2");
		order2.setFromCurrency("USD");
		order2.setToCurrency("GBP");
		order2.setAmount(100);
		order2.setOrderType(Order.ORDER_TYPE_SELL);
		order2.setOrderId("2");
		orderList.add(order2);
		
		Order order3 = new Order();
		order3.setUserId("user3");
		order3.setFromCurrency("GBP");
		order3.setToCurrency("USD");
		order3.setAmount(50);
		order3.setOrderType(Order.ORDER_TYPE_BUY);
		order3.setOrderId("3");
		orderList.add(order3);
		
		Order order4 = new Order();
		order4.setUserId("user4");
		order4.setFromCurrency("GBP");
		order4.setToCurrency("USD");
		order4.setAmount(50);
		order4.setOrderType(Order.ORDER_TYPE_BUY);
		order4.setOrderId("4");
		orderList.add(order4);
		
		Order order5 = new Order();
		order5.setUserId("user5");
		order5.setFromCurrency("USD");
		order5.setToCurrency("GBP");
		order5.setAmount(50);
		order5.setOrderType(Order.ORDER_TYPE_SELL);
		order5.setOrderId("5");
		orderList.add(order5);
		
		Order order6 = new Order();
		order6.setUserId("user5");
		order6.setFromCurrency("USD");
		order6.setToCurrency("GBP");
		order6.setAmount(20);
		order6.setOrderType(Order.ORDER_TYPE_SELL);
		order6.setOrderId("6");
		orderList.add(order6);
		return orderList;
	}
	
}

