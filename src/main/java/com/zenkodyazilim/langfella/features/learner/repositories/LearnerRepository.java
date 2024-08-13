package com.zenkodyazilim.langfella.features.learner.repositories;

import com.zenkodyazilim.langfella.features.learner.entities.Learner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
}
