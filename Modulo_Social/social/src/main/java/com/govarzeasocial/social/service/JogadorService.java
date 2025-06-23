package com.govarzeasocial.social.service;

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

    @Autowired
    private PessoaService pessoaService;


    public Jogador insert(Jogador jogador) {
        Pessoa jogadordb = pessoaService.findByCpf(jogador.getCpf());
        jogador.setPessoa(jogadordb);
        return jogadorRepo.save(jogador);
    }

    public List<Jogador> findAll() {
        return jogadorRepo.findAll();
    }

    public Jogador findByCpf(String cpf) {
        return jogadorRepo.findByCpf(cpf);
    }

    public List<Jogador> findByNome(String nome) {
        return jogadorRepo.findAllByPessoa_NomeContainingIgnoreCase(nome);
    }

    public Jogador edit(String id , Jogador jogador){
        Jogador jogadoredit = jogadorRepo.findByCpf(id);
        jogadoredit.setNumeroCamisa(jogador.getNumeroCamisa());
        jogadoredit.setApelido(jogador.getApelido());
        return jogadorRepo.save(jogadoredit);

    }

    public String delete(String cpf){
        Jogador jogadordb = jogadorRepo.findByCpf(cpf);
        jogadorRepo.deleteById(jogadordb.getJogadorID());
        return "Jogador deletado com sucesso";
    }

    public boolean checkJogador(String cpf){
        Jogador jogadordb = jogadorRepo.findByCpf(cpf);
        return jogadorRepo.findById(jogadordb.getJogadorID()).isPresent();
    }
}
