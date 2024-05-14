package com.malak.service;

import com.malak.model.Boss;
import com.malak.repository.BossRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BossService {

    @Autowired
    private BossRepository bossRepository;

    public Boss createAdmin(Boss boss) {
        return this.bossRepository.save(boss);
    }

    public Iterable<Boss> adminList() {
        return this.bossRepository.findAll();
    }

    public Integer getBossLogin(String name, String pass) {

        Optional<Boss> e = this.bossRepository.findByUserAndPassword(name, pass);
        if (e.isPresent()) {
            return e.get().getId();
        } else {
            return null;
        }

    }

}
