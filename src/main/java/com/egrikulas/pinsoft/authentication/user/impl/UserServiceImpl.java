package com.egrikulas.pinsoft.authentication.user.impl;

import com.egrikulas.pinsoft.authentication.auth.JwtService;
import com.egrikulas.pinsoft.authentication.user.api.AuthenticationRequest;
import com.egrikulas.pinsoft.authentication.user.api.AuthenticationResponse;
import com.egrikulas.pinsoft.authentication.user.api.RegisterRequest;
import com.egrikulas.pinsoft.authentication.user.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .userName(request.getUserName())
                .email(request.getEmail())
                .lastName(request.getLastName())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        var token = jwtService.generateToken(user);
        user.setToken(token);
        repository.save(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        var user = repository.findUserByUserName(request.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("kayıt bulunamadı!"));
        var token = jwtService.generateToken(user);
        user.setToken(token);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
