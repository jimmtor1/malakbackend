package com.malak.service;

import com.malak.model.Score;
import com.malak.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    public Score getOneScore(int idemploye, int year, int month){        
        return this.scoreRepository.findByEmployeIdAndYearAndMonth(idemploye, year, month);
    }
    
    public Iterable<Score> getScoreByYearAndMonth(int year, int month){        
        return this.scoreRepository.findByYearAndMonth(year, month); 
    }
    
}
