package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.CreateOrderRequest;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.response.ApiResponse;
import com.ecommerce.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {

		this.orderService = orderService;
	}

	@PostMapping
	public ApiResponse<Order> createOrder(@Valid @RequestBody CreateOrderRequest request) {

		return new ApiResponse<>(true, "Order created successfully", orderService.createOrder(request));
	}

	@GetMapping
	public ApiResponse<List<Order>> getAllOrders() {

		return new ApiResponse<>(true, "Orders fetched successfully", orderService.getAllOrders());
	}

	@GetMapping("/{id}")
	public ApiResponse<Order> getOrderById(@PathVariable("id") String id) {

		return new ApiResponse<>(true, "Order fetched successfully", orderService.getOrderById(id));
	}
}