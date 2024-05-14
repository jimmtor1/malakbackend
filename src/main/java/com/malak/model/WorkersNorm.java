
package com.malak.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="workers_norm")
public class WorkersNorm {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String element;
    
    private Integer quantity;
    
    private LocalTime startTime;

    private LocalTime finishTime;
    
    @Column(name="machine_number")
    private Integer machineNumber;  
    
    private String scoring;
    
}
