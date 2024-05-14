package com.malak.controller;

import com.malak.dto.JobPerformanceDTO;
import com.malak.dto.ScoreEmployeSumDTO;
import com.malak.dto.titleAndDataTableScoreMonth;
import com.malak.model.Coefficientb;
import com.malak.model.Employe;
import com.malak.model.OrderAssignment;
import com.malak.service.OrderAssignmentService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("assignment")
@CrossOrigin("*")
public class OrderAssignmentController {

    @Autowired
    private OrderAssignmentService orderAssignmentService;

    @PostMapping("/saveone")
    public OrderAssignment saveOrderAssignment(@RequestBody OrderAssignment orderAssignment) {
        return this.orderAssignmentService.assign(orderAssignment);
    }

    @PostMapping
    public boolean saveOrderAssignments(@RequestBody List<OrderAssignment> orderAssignments) {
        orderAssignments.forEach(a->{
            System.out.println(a);
        });
        return true;//this.orderAssignmentService.assigns(orderAssignments);
    }

    @GetMapping("{idemployee}")
    public Iterable<OrderAssignment> getOrderAssignments(@PathVariable int idemployee) {
        return this.orderAssignmentService.getAssignsByEmployeeAndRegistrationDate(idemployee, LocalDate.now());
    }

    @GetMapping("coefficient/{processingType}")
    public Coefficientb getOrderAssignments(@PathVariable String processingType) {
        return this.orderAssignmentService.getBCoefficient(processingType).orElse(null);
    }
    
    @GetMapping("resume/{idemployee}/{month}")
    public List<ScoreEmployeSumDTO> scoreSum(@PathVariable Integer idemployee, @PathVariable Integer month) {
        return this.orderAssignmentService.getScoreEmployeeList(new Employe(idemployee), month);
    }
    
    @GetMapping("average")
    public List<JobPerformanceDTO> scoreSum() {
        return this.orderAssignmentService.getLast3MonthsScoreEmployeeAvg();
    }
    
    @GetMapping("allMonthScore/{month}")
    public titleAndDataTableScoreMonth scoreSum(@PathVariable int month) {
        return this.orderAssignmentService.getScoreAllMonth(month);
    }

}
