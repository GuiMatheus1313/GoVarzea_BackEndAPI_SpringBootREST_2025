package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.Dirigente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirigenteRepo extends JpaRepository<Dirigente, Long> {
    //List<Dirigente> findByNomeContainingIgnoreCase(String nome);

    List<Dirigente> findAllByPessoa_NomeContainingIgnoreCase(String nome);
    Dirigente findByCpf(String cpf);
}
