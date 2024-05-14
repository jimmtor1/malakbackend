package com.malak.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="requisition")
public class Order implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id    
    private String id;
    
    private char suffix;
    
    @Column(name = "sub_id")
    private Integer subId;
        
    private Integer status;
    
    @ManyToOne()
    @JoinColumn(name="customer_id")
    private Customer customer;
    
    private LocalDate date;
    
    private boolean active;

    public Order() {
    }
                
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
//    @JoinColumn(name = "post_id")
//    private List<Element> element = new ArrayList<>();                

    public Order(String id, char suffix, Integer subId, Integer status, Customer customer, LocalDate date) {
        this.id = id;
        this.suffix = suffix;
        this.subId = subId;
        this.status = status;
        this.customer = customer;
        this.date = date;
    }
    
    public Order(String id){
        this.id = id;
    }
    
}
