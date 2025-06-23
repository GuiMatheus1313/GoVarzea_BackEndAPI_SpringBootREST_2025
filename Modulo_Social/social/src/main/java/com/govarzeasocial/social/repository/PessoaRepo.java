package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepo extends JpaRepository<Pessoa, Long> {
    //Para autenticação
    Optional<Pessoa> findByEmail(String email);
    Pessoa findByCpf(String cpf);
}
