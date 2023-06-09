package com.egrikulas.pinsoft.authentication.user.impl;

import com.egrikulas.pinsoft.authentication.user.api.AuthenticationRequest;
import com.egrikulas.pinsoft.authentication.user.api.AuthenticationResponse;
import com.egrikulas.pinsoft.authentication.user.api.RegisterRequest;
import com.egrikulas.pinsoft.authentication.user.api.UserService;
import com.egrikulas.pinsoft.library.rest.BaseController;
import com.egrikulas.pinsoft.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService service;

    @PostMapping("/register")
    public Response<AuthenticationResponse> register(
            @RequestBody RegisterRequest request){
        return respond(service.register(request));
    }

    @PostMapping("/authenticate")
    public Response<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request){
        return respond(service.authenticate(request));

    }
}
