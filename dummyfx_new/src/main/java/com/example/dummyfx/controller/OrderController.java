package com.example.dummyfx.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dummyfx.model.Order;
import com.example.dummyfx.service.OrderService;

@RestController

public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value="/upsertOrder", method = RequestMethod.POST)
	public ResponseEntity<Object> registerOrder(@Valid @RequestBody Order order) 
	{
		  if(orderService.registerOrder(order))
	      {
				return new ResponseEntity<>("Order is created successfully", HttpStatus.CREATED);
	      }
		  else
		  {
			  return new ResponseEntity<>("Error in creating order", HttpStatus.BAD_REQUEST);
		  }
	}
	
	 @RequestMapping(value = "/orders")
	   public ResponseEntity<Object> getOrders() {
		 List<Order> orders = orderService.getAllOrders();
		 if(orders.isEmpty())
		 {
			 return new ResponseEntity<>("No Orders to display", HttpStatus.OK);
		 }
	      return new ResponseEntity<>(orders, HttpStatus.OK);
	   }

	 @RequestMapping(value="/cancelOrder/{id}", method=RequestMethod.PUT)
	 public ResponseEntity<Object> cancelOrder(@PathVariable String id)
	 {
		 if(orderService.cancelOrder(id))
		 {
			 return new ResponseEntity<>("Order cancelled successfully", HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<>("Order with orderId : "+id+" does not exist.", HttpStatus.OK);
		 }
	 }
	 
	 @RequestMapping(value="matchedTrades", method=RequestMethod.GET)
	 public ResponseEntity<Object> matchedTradesReport()
	 {
		 List<Order> orders = orderService.getMatchedOrders();
		 if(orders.isEmpty())
		 {
			 return new ResponseEntity<>("There are no matched orders", HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<>(orders, HttpStatus.OK);
		 }
	 }
	 
	 @RequestMapping(value="unmatchedTrades", method=RequestMethod.GET)
	 public ResponseEntity<Object> unmatchedTradesReport()
	 {
		 List<Order> orders = orderService.getUnmatchedOrders();
		 if(orders.isEmpty())
		 {
			 return new ResponseEntity<>("There are no matched orders", HttpStatus.OK);
		 }
		 else
		 {
			 return new ResponseEntity<>(orders, HttpStatus.OK);
		 }
	 }
}
