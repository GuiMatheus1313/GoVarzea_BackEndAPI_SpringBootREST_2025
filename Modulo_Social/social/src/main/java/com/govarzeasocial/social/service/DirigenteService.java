package com.govarzeasocial.social.service;

import com.govarzeasocial.social.model.Dirigente;
import com.govarzeasocial.social.model.Jogador;
import com.govarzeasocial.social.repository.DirigenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirigenteService {

    @Autowired
    private DirigenteRepo dirigenteRepo;

    public Dirigente insert(Dirigente dirigente) {
        return dirigenteRepo.save(dirigente);
    }

    public List<Dirigente> findAll() {
        return dirigenteRepo.findAll();
    }

    public Dirigente findById(String cpf) {
        return dirigenteRepo.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Dirigente não encontrado com CPF: " + cpf));
    }

    public Dirigente edit(String id , Dirigente dirigente){
        Dirigente dirigenteedit = dirigenteRepo.findById(id).get();
        dirigenteedit.setCargo(dirigente.getCargo());
        return dirigenteRepo.save(dirigenteedit);

    }

    public String delete(String cpf){
        dirigenteRepo.deleteById(cpf);
        return "Dirigente deletado com sucesso";
    }
}
