package com.malak.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JobPerformanceDTO {
    
    private Integer employeeId;
    private String name;
    private Double average1;
    private Double average2;
    private Double average3;
    
}
