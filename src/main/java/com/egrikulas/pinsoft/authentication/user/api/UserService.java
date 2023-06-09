package com.egrikulas.pinsoft.authentication.user.api;

public interface UserService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
