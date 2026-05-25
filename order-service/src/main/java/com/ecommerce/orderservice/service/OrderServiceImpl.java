package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.dto.CreateOrderRequest;
import com.ecommerce.orderservice.dto.OrderItemRequest;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.common.event.OrderCreatedEvent;
import com.ecommerce.orderservice.exception.ApiException;
import com.ecommerce.orderservice.producer.OrderEventProducer;
import com.ecommerce.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final OrderEventProducer orderEventProducer;

	public OrderServiceImpl(OrderRepository orderRepository, OrderEventProducer orderEventProducer) {

		this.orderRepository = orderRepository;
		this.orderEventProducer = orderEventProducer;
	}

	@Override
	public Order createOrder(CreateOrderRequest request) {

		List<OrderItem> items = request.getItems().stream().map(this::mapToOrderItem).toList();

		double totalAmount = items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

		Order order = new Order();

		order.setCustomerId(request.getCustomerId());
		order.setItems(items);
		order.setTotalAmount(totalAmount);
		order.setStatus("CREATED");
		order.setCreatedAt(LocalDateTime.now());

		Order savedOrder = orderRepository.save(order);

		OrderCreatedEvent event = new OrderCreatedEvent(savedOrder.getId(), savedOrder.getCustomerId(),
				savedOrder.getTotalAmount(), savedOrder.getStatus(), savedOrder.getCreatedAt());

		orderEventProducer.publishOrderCreatedEvent(event);

		return savedOrder;
	}

	@Override
	public List<Order> getAllOrders() {

		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(String id) {

		return orderRepository.findById(id).orElseThrow(() -> new ApiException("Order not found"));
	}

	private OrderItem mapToOrderItem(OrderItemRequest request) {

		return new OrderItem(request.getProductId(), request.getQuantity(), request.getPrice());
	}
}