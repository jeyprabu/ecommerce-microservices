package com.ecommerce.orderservice.producer;

import com.ecommerce.common.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

	private static final String TOPIC = "order-created";

	private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

	public OrderEventProducer(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {

		this.kafkaTemplate = kafkaTemplate;
	}

	public void publishOrderCreatedEvent(OrderCreatedEvent event) {

		kafkaTemplate.send(TOPIC, event);
	}
}