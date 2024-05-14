package com.malak.repository;

import com.malak.model.Element;
import com.malak.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface ElementRepository extends CrudRepository<Element, Integer> {
    
    Iterable<Element> findByOrder(Order order);
    
}
