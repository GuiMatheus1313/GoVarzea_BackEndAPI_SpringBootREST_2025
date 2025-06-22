package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.Torcedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorcedorRepo extends JpaRepository<Torcedor, String> {
}
