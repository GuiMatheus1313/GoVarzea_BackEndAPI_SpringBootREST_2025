package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.Dirigente;
import com.govarzeasocial.social.model.Jogador;
import com.govarzeasocial.social.model.Torcedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TorcedorRepo extends JpaRepository<Torcedor, Long> {
    Torcedor findByCpf(String cpf);
    List<Torcedor> findAllByPessoa_NomeContainingIgnoreCase(String nome);

}
