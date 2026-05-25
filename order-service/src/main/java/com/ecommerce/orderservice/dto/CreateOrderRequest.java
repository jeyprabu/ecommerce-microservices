package com.ecommerce.orderservice.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateOrderRequest {

	@NotNull
	private Long customerId;

	private List<OrderItemRequest> items;

	public CreateOrderRequest() {
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<OrderItemRequest> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}
}