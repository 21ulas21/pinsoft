package com.egrikulas.pinsoft.authentication.user.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
}
