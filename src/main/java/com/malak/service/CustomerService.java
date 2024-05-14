package com.malak.service;

import com.malak.dto.CustomerDTO;
import com.malak.model.Customer;
import com.malak.repository.CustomerRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return this.customerRepository.save(customer);

    }

    public Iterable<CustomerDTO> customerList() {
        return this.customerRepository.findAllDTO();
    }

    public Integer getCustomerLogin(String name, String pass) {
        
        Optional<Customer> e = this.customerRepository.findByNameAndPassword(name, pass);
        if (e.isPresent()) {
            return e.get().getId();
        } else {
            return null;
        }
        
    }

}
