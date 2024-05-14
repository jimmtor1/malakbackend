package com.malak.dto;

import com.malak.model.Customer;
import java.time.LocalDate;

public record OrderDto (String id, LocalDate date, Customer customer, int status){}
