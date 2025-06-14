package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.Dirigente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirigenteRepo extends JpaRepository<Dirigente, String> {
}
