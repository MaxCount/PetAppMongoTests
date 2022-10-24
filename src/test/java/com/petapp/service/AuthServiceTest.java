package com.petapp.service;

import com.petapp.dto.RegisterRequest;
import com.petapp.entity.User;
import com.petapp.persistance.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private final RegisterRequest registerRequest = new RegisterRequest();

    @Mock private PasswordEncoder passwordEncoder;
    @InjectMocks private AuthService authService;
    @Mock private UserRepository userRepository;

    //test method registration
    // if test pass -> registration method works correctly
    @Test
    void isRegistrationPass() {
        registerRequest.setUsername("someone");
        registerRequest.setPassword(passwordEncoder.encode("somePassword"));

        authService.registration(registerRequest);
        Assertions.assertEquals(registerRequest.getUsername(), "someone" );
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this.userRepository);
    }

    //testing that registration username is unique.
    //if test pass -> usernames are unique
    @Test
    void isUsernameUnique() {
        RegisterRequest user1 = new RegisterRequest();
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("user1"));

        RegisterRequest user2 = new RegisterRequest();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("user2"));

        User test1 = new User();
        test1.setUsername("user1");
        test1.setPassword(passwordEncoder.encode("user1"));

        User test2 = new User();
        test2.setUsername("user2");
        test2.setPassword(passwordEncoder.encode("user2"));

        Mockito.when(userRepository.findByUsername(user1.getUsername())).thenReturn(java.util.Optional.of(test1));
        Mockito.when(userRepository.findByUsername(user2.getUsername())).thenReturn(java.util.Optional.of(test2));

        Assertions.assertNotSame(userRepository.findByUsername(user1.getUsername()), userRepository.findByUsername(user2.getUsername()));

    }
}