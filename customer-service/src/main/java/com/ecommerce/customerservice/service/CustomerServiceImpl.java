package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.dto.CreateCustomerRequest;
import com.ecommerce.customerservice.dto.CustomerResponse;
import com.ecommerce.customerservice.entity.Customer;
import com.ecommerce.customerservice.exception.ApiException;
import com.ecommerce.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {

		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerResponse createCustomer(CreateCustomerRequest request) {

		if (customerRepository.existsByEmail(request.getEmail())) {

			throw new ApiException("Email already exists");
		}

		Customer customer = new Customer();

		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setEmail(request.getEmail());
		customer.setPhone(request.getPhone());

		Customer savedCustomer = customerRepository.save(customer);

		return mapToResponse(savedCustomer);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {

		return customerRepository.findAll().stream().map(this::mapToResponse).toList();
	}

	@Override
	public CustomerResponse getCustomerById(Long id) {

		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ApiException("Customer not found"));

		return mapToResponse(customer);
	}

	private CustomerResponse mapToResponse(Customer customer) {

		return new CustomerResponse(customer.getId(), customer.getFirstName(), customer.getLastName(),
				customer.getEmail(), customer.getPhone());
	}
}