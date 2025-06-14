package com.govarzeasocial.social.service;

import com.govarzeasocial.social.model.Jogador;
import com.govarzeasocial.social.repository.JogadorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepo jogadorRepo;

    public Jogador insert(Jogador jogador) {
        return jogadorRepo.save(jogador);
    }

    public List<Jogador> findAll() {
        return jogadorRepo.findAll();
    }

    public Jogador findById(String cpf) {
        return jogadorRepo.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com CPF: " + cpf));
    }
}
