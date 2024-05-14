package com.malak.service;

import com.malak.model.WorkersNorm;
import com.malak.repository.WorkersNormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkersNormService {
    
    @Autowired
    private WorkersNormRepository workersNormRepository;
    
    public WorkersNorm save(WorkersNorm workersNorm){        
        return this.workersNormRepository.save(workersNorm);        
    }
    
    
}
