package com.malak.controller;

import com.malak.model.Score;
import com.malak.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("score")
@CrossOrigin("*")
public class ScoreController {
    
    @Autowired
    private ScoreService scoreService;
    
    @GetMapping("{year}/{month}")
    public Iterable<Score> scoreByMonth(@PathVariable int year, @PathVariable int month){
        return this.scoreService.getScoreByYearAndMonth(year, month);
    }
    
    @GetMapping("{employe}/{year}/{month}")
    public Score scoreByEmployeAndMonth(@PathVariable int employe, @PathVariable int year, @PathVariable int month){
        return this.scoreService.getOneScore(employe, year, month);
    }
    
}
