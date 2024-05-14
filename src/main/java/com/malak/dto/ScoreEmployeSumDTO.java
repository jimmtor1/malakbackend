package com.malak.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ScoreEmployeSumDTO {

    private LocalDate registrationDate;       
    private Double score;
        
    
}




