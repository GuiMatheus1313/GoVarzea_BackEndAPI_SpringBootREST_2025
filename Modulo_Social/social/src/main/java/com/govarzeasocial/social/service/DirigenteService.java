package com.govarzeasocial.social.service;

import com.govarzeasocial.social.model.Dirigente;
import com.govarzeasocial.social.model.Pessoa;
import com.govarzeasocial.social.repository.DirigenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirigenteService {

    @Autowired
    private DirigenteRepo dirigenteRepo;

    @Autowired
    private PessoaService pessoaService;

    public Dirigente insert(Dirigente dirigente) {
        Pessoa dirigentedb = pessoaService.findByCpf(dirigente.getCpf());
        dirigente.setPessoa(dirigentedb);
        return dirigenteRepo.save(dirigente);
    }

    public List<Dirigente> findAll() {

        return dirigenteRepo.findAll();
    }

    public Dirigente findByCpf(String cpf) {
        return dirigenteRepo.findByCpf(cpf);
    }

    public List<Dirigente> findByNome(String nome) {
        return dirigenteRepo.findAllByPessoa_NomeContainingIgnoreCase(nome);
    }

    public Dirigente edit(String id , Dirigente dirigente){
        Dirigente dirigenteedit = dirigenteRepo.findByCpf(id);
        dirigenteedit.setCargo(dirigente.getCargo());
        return dirigenteRepo.save(dirigenteedit);

    }

    public String delete(String cpf){
        Dirigente dirigentedb = dirigenteRepo.findByCpf(cpf);
        dirigenteRepo.deleteById(dirigentedb.getDirigenteID());
        return "Dirigente deletado com sucesso";
    }



    public boolean checkDirigente(String cpf){
        Dirigente dirigentedb = dirigenteRepo.findByCpf(cpf);
        return dirigenteRepo.findById(dirigentedb.getDirigenteID()).isPresent();
    }
}
