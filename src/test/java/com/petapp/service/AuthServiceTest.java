//package com.petapp.service;
//
//import com.petapp.entity.User;
//import com.petapp.persistance.UserRepository;
//import com.petapp.persistance.VerificationTokenRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.UUID;
//
//class AuthServiceTest {
//
//    @Mock private PasswordEncoder passwordEncoder;
//    @Mock private UserRepository userRepository;
//    private AutoCloseable autoCloseable;
//    private AuthService authService;
//    private VerificationTokenRepository verificationTokenRepository;
//
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() throws Exception {
//    }
//
//    private String generateVerificationToken(User user){
//        String token = UUID.randomUUID().toString();
//        User verificationToken = authService.getUser(user.getUsername());
//        verificationToken.setToken(token);
//
//        verificationTokenRepository.save(verificationToken);
//        return token;
//    }
//
//    @Test
//    @Rollback(value = false)
//    void registration() {
//
//        User user = new User();
//        user.setUserId("6346f891667f622859cae6f8");
//        user.setUsername("someone");
//        user.setPassword(passwordEncoder.encode("somePassword"));
//
//        userRepository.save(user);
//        user.setToken(generateVerificationToken(user));
//
//
//
//        Assertions.assertEquals(user.getUserId(), "6346f891667f622859cae6f8");
//    }
//
//    @Test
//    void login() {
//    }
//}