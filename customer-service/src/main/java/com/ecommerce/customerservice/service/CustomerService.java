package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.dto.CreateCustomerRequest;
import com.ecommerce.customerservice.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

	CustomerResponse createCustomer(CreateCustomerRequest request);

	List<CustomerResponse> getAllCustomers();

	CustomerResponse getCustomerById(Long id);
}