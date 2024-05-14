package com.malak.repository;

import com.malak.dto.OrderDto;
import com.malak.model.Customer;
import com.malak.model.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    
    Iterable<OrderDto> findByStatusAndActiveTrue(int status);    
//    Iterable<Order> findByCustomerId(int idcustomer); 
    Iterable<Order> findByCustomer(Customer idcustomer); 
    
    @Query("SELECT o FROM Order o WHERE o.suffix = :suffix ORDER BY o.subId DESC LIMIT 1")
    Optional<Order> findByLastSuffix(@Param("suffix") char suffix);
    
    Optional<Order> findById(String status); 
        
    Iterable<OrderDto> findByStatusGreaterThanAndActiveTrueOrderByDateDesc(int status);   
    
    
    
}
