package com.code_challenger_natixis.storeAPI.repository;

import com.code_challenger_natixis.storeAPI.data.entities.user.UserPsql;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserPsql, Long> {

    Optional<UserPsql> findByUsername(String username);
}
