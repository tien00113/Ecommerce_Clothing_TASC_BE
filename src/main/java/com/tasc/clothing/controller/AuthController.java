package com.tasc.clothing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasc.clothing.model.User;
import com.tasc.clothing.request.LoginRequest;
import com.tasc.clothing.response.AuthResponse;
import com.tasc.clothing.service.AuthService;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/auth/signup")
    public AuthResponse registerUser(@RequestBody User user) throws Exception{
        return authService.createUser(user);
    }

    @PostMapping("/auth/signin")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        return authService.signin(loginRequest);
    }

    @GetMapping("auth/logout")
    public String logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "đã đăng xuất";
    }
}
