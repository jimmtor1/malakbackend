package com.malak.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class titleAndDataTableScoreMonth {
    
    private List<LocalDate> dates;
    private Object[][] scores;
    
}
