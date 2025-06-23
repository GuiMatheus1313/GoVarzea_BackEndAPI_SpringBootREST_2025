package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRepo extends JpaRepository<Time, Long> {
    List<Time> findAllByNomeContainingIgnoreCase(String nome);
}
