package com.malak.service;

import com.malak.model.Employe;
import com.malak.repository.EmployeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    public Employe createEmploye(Employe employe) {
        return this.employeRepository.save(employe);
    }

    //para mostrar la lista de empleados activos
    public Iterable<Employe> employeList() {
        return this.employeRepository.findByActive(true);
//        return this.employeRepository.findAll();
    }
    
    //para mostrar la lista de empleados activos
    public Iterable<Employe> firedEmployeList() {
        return this.employeRepository.findByActive(false);
//        return this.employeRepository.findAll();
    }

    public Employe getOneEmploye(int idemploye) {
        return this.employeRepository.findById(idemploye).get();
    }

    public Integer getEmployeLogin(String name, String pass) {
        Optional<Employe> e = this.employeRepository.findByNameAndPassword(name, pass);
        if (e.isPresent()) {
            return e.get().getId();
        } else {
            return null;
        }
    }

}
