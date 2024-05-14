package com.malak.repository;

import com.malak.dto.CustomerDTO;
import com.malak.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    
    Optional<Customer> findByNameAndPassword(String name, String pass);
    
    @Query("SELECT new com.malak.dto.CustomerDTO(c.id, c.name) FROM Customer c")
    Iterable<CustomerDTO> findAllDTO();
     
}
