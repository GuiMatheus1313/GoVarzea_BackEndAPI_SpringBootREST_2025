package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.TimeJogadores;
import com.govarzeasocial.social.model.TimeJogadoresPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeJogadoresRepo extends JpaRepository<TimeJogadores, TimeJogadoresPK> {
    List<TimeJogadores> findByTimeJogadoresPK_TimeIdPK(Long timeIdPK);

}
