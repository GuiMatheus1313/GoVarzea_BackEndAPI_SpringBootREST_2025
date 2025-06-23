package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.Dirigente;
import com.govarzeasocial.social.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadorRepo extends JpaRepository<Jogador, Long> {
    //List<Jogador> findByNomeContainingIgnoreCase(String nome);

    List<Jogador> findAllByPessoa_NomeContainingIgnoreCase(String nome);
    Jogador findByCpf(String cpf);

}
