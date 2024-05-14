package com.malak.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Setter
public class Coefficientb {

    @Column(name = "processing_type")
    @Id
    private String processingType;
    
    private float coefficient;
    
    private String name;
    
    
}
