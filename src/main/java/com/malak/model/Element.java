package com.malak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@Entity
@Table(name="element")
public class Element{
            
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String material;
    private float length;    
    private float width;    
    private float height;
    private int quantity;
    private int assigned =0;    
    
    @ManyToOne()
    @JoinColumn(name="order_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)    
    private Order order;
    
}
