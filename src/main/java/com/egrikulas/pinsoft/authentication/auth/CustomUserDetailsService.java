package com.egrikulas.pinsoft.authentication.auth;

import com.egrikulas.pinsoft.authentication.user.impl.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findUserByUserName(username).orElseThrow(()-> new UsernameNotFoundException("Kayıt bulunamadı!"));
        return new CustomUserDetails(user.getUserName(), user.getPassword(), user.getRole().toString());
    }
}
