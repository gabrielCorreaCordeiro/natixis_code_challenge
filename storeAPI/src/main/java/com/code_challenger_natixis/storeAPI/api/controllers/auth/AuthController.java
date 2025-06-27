package com.code_challenger_natixis.storeAPI.api.controllers.auth;

import com.code_challenger_natixis.storeAPI.api.payloads.request.AuthRequest;
import com.code_challenger_natixis.storeAPI.api.payloads.response.AuthResponse;
import com.code_challenger_natixis.storeAPI.api.payloads.response.UserResponse;
import com.code_challenger_natixis.storeAPI.api.utils.JwtService;
import com.code_challenger_natixis.storeAPI.domain.entities.User;
import com.code_challenger_natixis.storeAPI.domain.exceptions.InvalidRequestException;
import com.code_challenger_natixis.storeAPI.domain.usecases.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authManager;

    private final JwtService jwtService;

    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        Authentication authUser = authManager.authenticate(usernamePassword);

        String token = jwtService.generateToken((User) authUser.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) throws InvalidRequestException {

        createUserUseCase.create(request.toUser());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
