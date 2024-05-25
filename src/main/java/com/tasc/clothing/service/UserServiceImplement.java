package com.tasc.clothing.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasc.clothing.config.JwtProvider;
import com.tasc.clothing.exception.UserException;
import com.tasc.clothing.model.User;
import com.tasc.clothing.model.User.Role;
import com.tasc.clothing.repository.UserRepository;
 
@Service
public class UserServiceImplement implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserById(Long userId) throws UserException {

        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);

        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User user, Long userId) throws UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public User setRolesUser(String adminUsername, String targetUsername, Collection<Role> roles) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRolesUser'");
    }

    @Override
    public List<User> getAllusers() {

        return userRepository.findAll();
    }
    
}
