package com.malak.controller;

import com.malak.dto.LoginDTO;
import com.malak.service.AdminService;
import com.malak.service.BossService;
import com.malak.service.CustomerService;
import com.malak.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@CrossOrigin("*")
public class LoginController  {
    
    @Autowired
    private EmployeService employeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private BossService bossService;
    
    @PostMapping
    public Integer saveEmploye(@RequestBody LoginDTO login){
        
        if(null == login.getRole()){
            return null;
        }else return switch (login.getRole()) {
            case "employee" -> this.employeService.getEmployeLogin(login.getUser(), login.getPass());
            case "customer" -> this.customerService.getCustomerLogin(login.getUser(), login.getPass());
            case "admin" -> this.adminService.getAdminLogin(login.getUser(), login.getPass());
            case "boss" -> this.bossService.getBossLogin(login.getUser(), login.getPass());
            default -> null;
        };
        
    }
    
}
