package com.govarzeasocial.social.service;

import com.govarzeasocial.social.model.Pessoa;
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

    @Autowired
    private PessoaService pessoaService;


    public TorcedorService(TorcedorRepo torcedorRepository) {
        this.torcedorRepository = torcedorRepository;
    }

    public List<Torcedor> findAll() {
        return torcedorRepository.findAll();
    }

    public Torcedor findByCpf(String cpf) {
        return torcedorRepository.findByCpf(cpf);
    }

    public List<Torcedor> findByNome(String nome){
        return torcedorRepository.findAllByPessoa_NomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Torcedor insert(Torcedor torcedor) {
        Pessoa torcerdb = pessoaService.findByCpf(torcedor.getCpf());

        torcedor.setPessoa(torcerdb);
        return torcedorRepository.save(torcedor);
    }

    @Transactional
    public Torcedor edit(String cpf, Torcedor torcedor) {
        Torcedor torcerdb = torcedorRepository.findByCpf(cpf);
        torcerdb.setBiografia(torcedor.getBiografia());
        return torcedorRepository.save(torcerdb);
    }

    @Transactional
    public String delete(String cpf) {
        Torcedor torcerdb = torcedorRepository.findByCpf(cpf);
        torcedorRepository.deleteById(torcerdb.getTorcedorID());
        return "Torcedor deletado com sucesso!";
    }

    public boolean checkTorcedor(String cpf){
        Torcedor torcedordb = torcedorRepository.findByCpf(cpf);
        return torcedorRepository.findById(torcedordb.getTorcedorID()).isPresent();
    }

}
