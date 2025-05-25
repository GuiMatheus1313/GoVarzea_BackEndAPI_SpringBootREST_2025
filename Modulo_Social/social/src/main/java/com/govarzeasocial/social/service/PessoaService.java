package com.govarzeasocial.social.service;

import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.repository.PessoaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepo pessoaRepo;

    public List<Pessoa> findall(){
        return pessoaRepo.findAll();
    }

    public Pessoa findById(String cpf){
        return pessoaRepo.findById(cpf).orElseThrow(null);
    }

    public Pessoa insert(Pessoa pessoa){
        return pessoaRepo.save(pessoa);
    }

    public Pessoa edit(String id , Pessoa pessoa){
        Pessoa pessoaedit = pessoaRepo.findById(id).get();
        pessoaedit.setNome(pessoa.getNome());
        pessoaedit.setEmail(pessoa.getEmail());
        pessoaedit.setTelefone(pessoa.getTelefone());
        return pessoaRepo.save(pessoaedit);

    }

    public String delete(String cpf){
        pessoaRepo.deleteById(cpf);
        return "Pessoa deletada com sucesso";
    }


}
