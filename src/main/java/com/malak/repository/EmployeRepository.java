package com.malak.repository;

import com.malak.model.Employe;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface EmployeRepository extends CrudRepository<Employe, Integer>{
    
    Optional<Employe> findByNameAndPassword(String name, String pass);
    
    Iterable<Employe> findByActive(boolean active);
    
}
