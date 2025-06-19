package com.govarzeasocial.social.repository;

import com.govarzeasocial.social.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepo extends JpaRepository<Time, Long> {
}
