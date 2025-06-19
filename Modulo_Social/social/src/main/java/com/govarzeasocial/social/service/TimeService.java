package com.govarzeasocial.social.service;

import com.govarzeasocial.social.model.Time;
import com.govarzeasocial.social.repository.TimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    private TimeRepo timeRepo;

    public List<Time> findAll() {
        return timeRepo.findAll();
    }

    public Time findById(Long id) {
        return timeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado com ID: " + id));
    }

    public Time insert(Time time) {
        return timeRepo.save(time);
    }

    public Time edit(Long id, Time updatedTime) {
        Time time = timeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado com ID: " + id));

        time.setNome(updatedTime.getNome());
        time.setLocalizacao(updatedTime.getLocalizacao());
        time.setFundacao(updatedTime.getFundacao());

        return timeRepo.save(time);
    }

    public String delete(Long id) {
        timeRepo.deleteById(id);
        return "Time deletado com sucesso.";
    }
}
