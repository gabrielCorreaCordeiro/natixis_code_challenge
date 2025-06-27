package com.code_challenger_natixis.storeAPI.domain.repository;

import com.code_challenger_natixis.storeAPI.domain.entities.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUserName(String userName);
}
