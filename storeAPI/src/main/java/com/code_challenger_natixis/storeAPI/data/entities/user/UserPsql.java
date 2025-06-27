package com.code_challenger_natixis.storeAPI.data.entities.user;

import com.code_challenger_natixis.storeAPI.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "user_system")
@Getter
@Setter
@NoArgsConstructor
public class UserPsql {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;





    public UserPsql(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
