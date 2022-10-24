package com.petapp.service;

import com.petapp.dto.AuthenticationResponse;
import com.petapp.dto.LoginRequest;
import com.petapp.dto.RegisterRequest;
import com.petapp.entity.User;
import com.petapp.persistance.UserRepository;
import com.petapp.persistance.VerificationTokenRepository;
import com.petapp.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final VerificationTokenRepository verificationTokenRepository;
    private final JwtUtil jwtUtil;

    public void registration(RegisterRequest registerRequest){

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setToken(UUID.randomUUID().toString());

        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtUtil.generateAccessToken(getUser());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(generateVerificationToken(getUser()))
                .expiresAt(Instant.now().plusMillis(jwtUtil.getEXPIRE_DURATION()))
                .username(loginRequest.getUsername())
                .build();
    }

    @Transactional(readOnly = true)
    public User getUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with name - " + principal.getUsername() + " not found"));
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + username));
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        User verificationToken = getUser(user.getUsername());
        verificationToken.setToken(token);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}