package com.tasc.clothing.service;

import java.util.Collection;
import java.util.List;

import com.tasc.clothing.exception.UserException;
import com.tasc.clothing.model.User;

public interface UserService {
    public User registerUser(User user);
    
    public User findUserById(Long userId) throws UserException;

    public User findUserByEmail(String email);

    public User findUserByJwt(String jwt);

    public User updateUser(User user, Long userId) throws UserException;

    public User setRolesUser(String adminUsername, String targetUsername, Collection<User.Role> roles);

    public List<User> getAllusers();
}
