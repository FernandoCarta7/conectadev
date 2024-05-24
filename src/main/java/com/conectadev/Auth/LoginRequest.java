package com.conectadev.Auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    String username;
    String password; 
}
