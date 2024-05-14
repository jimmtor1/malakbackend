package com.malak.controller;

import com.malak.dto.CustomerDTO;
import com.malak.model.Customer;
import com.malak.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
@CrossOrigin("*")
public class CustormerController {
    
    @Autowired
    private CustomerService customerService;
    
    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer){
        return this.customerService.createCustomer(customer);
    }
    
    @GetMapping
    public Iterable<CustomerDTO> getCustomerList(){
        return this.customerService.customerList();
    }
    
}
