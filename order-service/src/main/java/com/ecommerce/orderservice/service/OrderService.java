package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.CreateOrderRequest;
import com.ecommerce.orderservice.entity.Order;

import java.util.List;

public interface OrderService {

	Order createOrder(CreateOrderRequest request);

	List<Order> getAllOrders();

	Order getOrderById(String id);
}