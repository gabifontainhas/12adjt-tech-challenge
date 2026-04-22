package br.com.gabifontainhas.techchallenge.service;

import br.com.gabifontainhas.techchallenge.entity.Customer;
import br.com.gabifontainhas.techchallenge.exception.EmailAlreadyExistsException;
import br.com.gabifontainhas.techchallenge.exception.UserNotFoundException;
import br.com.gabifontainhas.techchallenge.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
        if (customerRepository.existsUserByEmail(customer.getEmail())) {
            throw new EmailAlreadyExistsException("E-mail already exists");
        } else {
            return this.customerRepository.save(customer);
        }
    }

    public Customer update(Long id, Customer updatedCustomer) {
        var customer = customerRepository.findCustomerById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        customer.update(updatedCustomer);
        return this.customerRepository.save(customer);
    }

    public List<Customer> findAll(int size, int offset) {
        return customerRepository.findAll(size, offset);
    }

}
