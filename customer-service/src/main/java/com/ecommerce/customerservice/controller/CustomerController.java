package com.ecommerce.customerservice.controller;

import com.ecommerce.customerservice.dto.CreateCustomerRequest;
import com.ecommerce.customerservice.dto.CustomerResponse;
import com.ecommerce.customerservice.response.ApiResponse;
import com.ecommerce.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {

		this.customerService = customerService;
	}

	@PostMapping
	public ApiResponse<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {

		CustomerResponse response = customerService.createCustomer(request);

		return new ApiResponse<>(true, "Customer created successfully", response);
	}

	@GetMapping
	public ApiResponse<List<CustomerResponse>> getAllCustomers() {

		return new ApiResponse<>(true, "Customers fetched successfully", customerService.getAllCustomers());
	}

	@GetMapping("/{id}")
	public ApiResponse<CustomerResponse> getCustomerById(@PathVariable("id") Long id) {

		return new ApiResponse<>(true, "Customer fetched successfully", customerService.getCustomerById(id));
	}
}