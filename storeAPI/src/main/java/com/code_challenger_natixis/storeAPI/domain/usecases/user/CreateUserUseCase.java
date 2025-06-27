package com.code_challenger_natixis.storeAPI.domain.usecases.user;

import com.code_challenger_natixis.storeAPI.data.repository.user.PsqlUserRepository;
import com.code_challenger_natixis.storeAPI.domain.entities.User;
import com.code_challenger_natixis.storeAPI.domain.exceptions.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final PsqlUserRepository psqlUserRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(User user) throws InvalidRequestException {

        if(validateUser(user.getUsername())) {
            throw new InvalidRequestException("User already exists");
        }

        User userToSave = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();

        return psqlUserRepository.create(userToSave);
    }


    private Boolean validateUser(String username) {
        return psqlUserRepository.findByUserName(username).isPresent();
    }
}
