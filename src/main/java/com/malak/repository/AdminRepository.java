package com.malak.repository;

import com.malak.model.Admin;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


public interface AdminRepository extends CrudRepository<Admin, Integer> {
    
    Optional<Admin> findByUserAndPassword(String name, String pass);
    
}
