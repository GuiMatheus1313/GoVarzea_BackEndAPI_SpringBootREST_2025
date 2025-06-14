package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepo extends JpaRepository<Jogador, String> {

}
