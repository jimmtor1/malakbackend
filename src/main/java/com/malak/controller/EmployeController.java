package com.malak.controller;

import com.malak.model.Employe;
import com.malak.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("employee")
@CrossOrigin("*")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @PostMapping
    public Employe saveEmploye(@RequestBody Employe employe) {
        System.out.println(employe);
        return this.employeService.createEmploye(employe);
    }

    @GetMapping
    public Iterable<Employe> employeList() {
        return this.employeService.employeList();
    }

    @GetMapping("{employe}")
    public Employe getEmploye(@PathVariable Integer employe) {
        return this.employeService.getOneEmploye(employe);
    }

    @GetMapping("fired/{employee}")
    public void firedEmployee(@PathVariable Integer employee) {
        Employe emp = this.employeService.getOneEmploye(employee);
        emp.setActive(false);
        this.employeService.createEmploye(emp);
    }

    @GetMapping("fired")
    public Iterable<Employe> firedEmployeeList() {
        return this.employeService.firedEmployeList();
    }

}
