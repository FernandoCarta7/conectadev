package com.conectadev.Auth;

import com.conectadev.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String firstname;
    String lastname;
    String country;
    Role role;
}
