package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepo extends JpaRepository<Pessoa, String> {
}
