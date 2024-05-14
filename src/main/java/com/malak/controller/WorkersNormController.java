package com.malak.controller;

import com.malak.model.WorkersNorm;
import com.malak.service.WorkersNormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/url")
@CrossOrigin("*")
public class WorkersNormController {
    
    @Autowired
    private WorkersNormService workersNormService;
    
    @PostMapping
    public WorkersNorm createWorkersNorm(WorkersNorm workersNorm){        
        return this.workersNormService.save(workersNorm);        
    }
    
}
