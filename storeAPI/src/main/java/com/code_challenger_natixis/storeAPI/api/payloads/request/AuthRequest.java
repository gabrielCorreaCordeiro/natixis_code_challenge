package com.code_challenger_natixis.storeAPI.api.payloads.request;

import com.code_challenger_natixis.storeAPI.domain.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequest {


    private String username;
    private String password;

    public AuthRequest(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }

}
