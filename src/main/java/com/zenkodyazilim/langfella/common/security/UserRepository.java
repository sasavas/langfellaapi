package com.zenkodyazilim.langfella.common.security;

import com.zenkodyazilim.langfella.common.security.entities.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CustomUser, Long> {
    Optional<CustomUser> findByUsername(String username);
    boolean existsByUsername(String username);
}