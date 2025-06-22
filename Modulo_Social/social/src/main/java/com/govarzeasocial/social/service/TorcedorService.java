package com.govarzeasocial.social.service;

import com.govarzeasocial.social.model.Torcedor;
import com.govarzeasocial.social.repository.TorcedorRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TorcedorService {

    @Autowired
    private TorcedorRepo torcedorRepository;


    public TorcedorService(TorcedorRepo torcedorRepository) {
        this.torcedorRepository = torcedorRepository;
    }

    public List<Torcedor> findAll() {
        return torcedorRepository.findAll();
    }

    public Torcedor findById(String cpf) {
        return torcedorRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Torcedor não encontrado com CPF: " + cpf));
    }

    @Transactional
    public Torcedor insert(Torcedor torcedor) {
        return torcedorRepository.save(torcedor);
    }

    @Transactional
    public void deleteById(String cpf) {
        torcedorRepository.deleteById(cpf);
    }

}
