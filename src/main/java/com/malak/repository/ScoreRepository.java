package com.malak.repository;

import com.malak.model.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Integer> {
    
    Score findByEmployeIdAndYearAndMonth(int employeid, int year, int month);
    Iterable<Score> findByYearAndMonth(int year, int month);
    
}
