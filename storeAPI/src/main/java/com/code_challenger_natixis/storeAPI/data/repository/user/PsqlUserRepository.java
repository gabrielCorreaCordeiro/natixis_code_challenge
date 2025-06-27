package com.code_challenger_natixis.storeAPI.data.repository.user;

import com.code_challenger_natixis.storeAPI.data.entities.user.UserPsql;
import com.code_challenger_natixis.storeAPI.domain.entities.User;
import com.code_challenger_natixis.storeAPI.domain.repository.UserRepository;
import com.code_challenger_natixis.storeAPI.repository.JpaUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PsqlUserRepository implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> findByUserName(String userName) {

        return jpaUserRepository.findByUsername(userName).map(UserPsql::toEntity);
    }

    public User create(User user) {
        return jpaUserRepository.save(new UserPsql(user)).toEntity();
    }
}
