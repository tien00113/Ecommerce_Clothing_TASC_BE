package com.tasc.clothing.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tasc.clothing.config.JwtProvider;
import com.tasc.clothing.model.User;
import com.tasc.clothing.repository.UserRepository;
import com.tasc.clothing.request.LoginRequest;
import com.tasc.clothing.response.AuthResponse;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    
    public AuthResponse createUser(User user) throws Exception {
        User isExist = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (isExist != null) {
            throw new Exception("Email hoặc username này đã được sử dụng bởi tài khoản khác");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<User.Role> userRoles = new HashSet<>(user.getRoles());
        userRoles.add(User.Role.USER);
        newUser.setRoles(userRoles);

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
                savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JwtProvider.generateToken(authentication);

        return new AuthResponse(token, "Đăng ký thành công");
    }

    public AuthResponse signin(LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);

        return new AuthResponse(token, "Đăng nhập thành công");
    }

    private Authentication authenticate(String usernameOrEmail, String password) {

        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(usernameOrEmail);

        if (userDetails == null) {
            throw new BadCredentialsException("username hoặc email sử dụng không hợp lệ");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Sai mật khẩu");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
