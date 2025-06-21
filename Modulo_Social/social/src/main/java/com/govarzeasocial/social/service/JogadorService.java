package com.govarzeasocial.social.service;

import com.govarzeasocial.social.model.Dirigente;
import com.govarzeasocial.social.model.Jogador;
import com.govarzeasocial.social.model.Pessoa;
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

    public List<Jogador> findByNome(String nome) {
        return jogadorRepo.findByNomeContainingIgnoreCase(nome);
    }

    public Jogador edit(String id , Jogador jogador){
        Jogador jogadoredit = jogadorRepo.findById(id).get();
        jogadoredit.setNumeroCamisa(jogador.getNumeroCamisa());
        jogadoredit.setApelido(jogador.getApelido());
        return jogadorRepo.save(jogadoredit);

    }

    public String delete(String cpf){
        jogadorRepo.deleteById(cpf);
        return "Jogador deletado com sucesso";
    }

    public boolean checkJogador(String cpf){
        return jogadorRepo.findById(cpf).isPresent();
    }
}
