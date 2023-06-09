package com.egrikulas.pinsoft.authentication.user.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {

    private String username;
    private String password;

}
