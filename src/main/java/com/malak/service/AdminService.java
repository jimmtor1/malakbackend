package com.malak.service;

import com.malak.model.Admin;
import com.malak.repository.AdminRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        return this.adminRepository.save(admin);
    }

    public Iterable<Admin> adminList() {
        return this.adminRepository.findAll();
    }

    public Integer getAdminLogin(String name, String pass) {

        Optional<Admin> e = this.adminRepository.findByUserAndPassword(name, pass);
        if (e.isPresent()) {
            return e.get().getId();
        } else {
            return null;
        }
    }

}
