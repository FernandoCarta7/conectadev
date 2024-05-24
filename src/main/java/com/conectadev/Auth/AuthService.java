package com.conectadev.Auth;

import com.conectadev.Jwt.JwtService;
import com.conectadev.User.Role;
import com.conectadev.User.User;
import com.conectadev.User.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username, request.password));
        UserDetails user=userRepository.findByUsername(request.username).orElseThrow();
        String token=jwtService.getToken(user);


        AuthResponse authResponse = new AuthResponse();
        authResponse.token = jwtService.getToken(user);

        return authResponse;
    }

    public AuthResponse register(RegisterRequest request) {

        User user = new User(request.username,
                passwordEncoder.encode(request.password),
                request.firstname,
                request.lastname,
                request.country,
                request.role);

        userRepository.save(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.token = jwtService.getToken(user);

        return authResponse;

    }

}
