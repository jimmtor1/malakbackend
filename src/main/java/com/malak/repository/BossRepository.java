package com.malak.repository;

import com.malak.model.Boss;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;


public interface BossRepository extends CrudRepository<Boss, Integer>  {
    
    Optional<Boss> findByUserAndPassword(String name, String pass);

}
