package com.example.springargumentresolver.controller;

import com.example.springargumentresolver.domain.User;
import com.example.springargumentresolver.jwt.JwtAuthorization;
import com.example.springargumentresolver.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @PostMapping("/test-login")
    public String testLogin(@JwtAuthorization User user) {
        log.info("User : {}", user);
        return user.toString();
    }

    @PostMapping("/test-token")
    public String testToken(@RequestBody User user) {
        log.info("User : {}", user);
        return new JwtProvider().createToken(user);
    }
}
