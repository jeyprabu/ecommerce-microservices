package com.ecommerce.common.event;

import java.time.LocalDateTime;

public class OrderCreatedEvent {

	private String orderId;

	private Long customerId;

	private Double totalAmount;

	private String status;

	private LocalDateTime createdAt;

	public OrderCreatedEvent() {
	}

	public OrderCreatedEvent(String orderId, Long customerId, Double totalAmount, String status,
			LocalDateTime createdAt) {

		this.orderId = orderId;
		this.customerId = customerId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.createdAt = createdAt;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}