package com.example.dummyfx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.dummyfx.model.Order;

@Service
public class OrderServiceImpl implements OrderService {

	public static Map<String, Order> orderMap = new HashMap<String, Order>();
	@Autowired
	private Environment env;
	
	@Override
	public boolean registerOrder(Order order) {
		if(StringUtils.isEmpty(order.getOrderId()))
		{
			order.setOrderId(UUID.randomUUID().toString());
		}
		order.setPrice(Double.parseDouble(env.getProperty("price")));
		orderMap.put(order.getOrderId(), order);
		return true;
	}

	@Override
	public boolean cancelOrder(String orderId) {
		if(orderMap.get(orderId)==null)
		{
			return false;
		}
		else
		{
			orderMap.get(orderId).setArchive(true);
			return true;
		}
	}

	@Override
	public List<Order> getAllOrders() {
		return new ArrayList<Order>(orderMap.values());
	}

	@Override
	public List<Order> getMatchedOrders() {
		return getMatchedUnmatchedOrders().get("MATCHED");
	}

	@Override
	public List<Order> getUnmatchedOrders() {
		return getMatchedUnmatchedOrders().get("UNMATCHED");
	}
	
	public Map<String, List<Order>> getMatchedUnmatchedOrders()
	{
		Map<String, List<Order>> sellOrderMap = new HashMap<String, List<Order>>();
		Map<String, List<Order>> buyOrderMap = new HashMap<String, List<Order>>();
		List<Order> matchedOrders = new ArrayList<Order>();
		List<Order> unmatchedOrders =new ArrayList<Order>();
		for (Entry<String, Order> orderEntry : orderMap.entrySet()) 
		{
			Order order = orderEntry.getValue();
			String key = null;
			if(!order.isArchive())
			{
				if(order.getOrderType().contentEquals(Order.ORDER_TYPE_BUY))
				{
					key = order.getToCurrency()+order.getFromCurrency()+order.getAmount();
					if(buyOrderMap.get(key) == null)
					{
						buyOrderMap.put(key, new ArrayList<Order>());
					}
					buyOrderMap.get(key).add(order);
				}
				else
				{
					key = order.getFromCurrency()+order.getToCurrency()+order.getAmount();
					if(sellOrderMap.get(key)==null)
					{
						sellOrderMap.put(key, new ArrayList<Order>());
					}
					sellOrderMap.get(key).add(order);
				}
			}
		}	
		for (Entry<String, List<Order>> orderEntry : sellOrderMap.entrySet()) 
		{
			if(buyOrderMap.get(orderEntry.getKey())!=null)
			{
				List<Order> buyOrders = buyOrderMap.get(orderEntry.getKey());
				if(orderEntry.getValue().size() <= buyOrders.size())
				{
					int index=0;
					for (Order order : orderEntry.getValue()) 
					{
						matchedOrders.add(order);
						matchedOrders.add(buyOrders.get(index));
					}
					unmatchedOrders.addAll(buyOrders.subList(index, buyOrders.size()-1));
				}
				else
				{
					int index=0;
					for (Order order : buyOrders) 
					{
						matchedOrders.add(order);
						matchedOrders.add(orderEntry.getValue().get(index));
					}
					unmatchedOrders.addAll(orderEntry.getValue().subList(index, buyOrders.size()-1));
				}
			}
			else
			{
				unmatchedOrders.addAll(orderEntry.getValue());
			}
		}
		Map<String, List<Order>> matchedUnmatchedOrders = new HashMap<String, List<Order>>();
		matchedUnmatchedOrders.put("MATCHED", matchedOrders);
		matchedUnmatchedOrders.put("UNMATCHED", unmatchedOrders);
		return matchedUnmatchedOrders;
	}

}
