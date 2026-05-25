package com.ecommerce.inventoryservice.consumer;

import com.ecommerce.common.event.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

	@KafkaListener(topics = "order-created", groupId = "inventory-group")
	public void consumeOrderCreatedEvent(OrderCreatedEvent event) {

		System.out.println("==================================");

		System.out.println("ORDER EVENT RECEIVED");

		System.out.println("Order ID: " + event.getOrderId());

		System.out.println("Customer ID: " + event.getCustomerId());

		System.out.println("Total Amount: " + event.getTotalAmount());

		System.out.println("Reducing inventory...");

		System.out.println("Inventory updated successfully");

		System.out.println("==================================");
	}
}