package com.code_challenger_natixis.storeAPI.domain.usecases.user;


import com.code_challenger_natixis.storeAPI.data.repository.user.PsqlUserRepository;
import com.code_challenger_natixis.storeAPI.domain.entities.User;
import com.code_challenger_natixis.storeAPI.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReadUserUseCase {

    private final PsqlUserRepository psqlUserRepository;

    public User findUserByUserName(String username) {

        User user = psqlUserRepository.findByUserName(username).orElse(null);

        return user;
    }


}
