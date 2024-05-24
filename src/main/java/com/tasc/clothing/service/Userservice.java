package com.tasc.clothing.service;

import com.tasc.clothing.model.User;

public interface Userservice {
    public User createUser(User user);
    public User delete_User(User user , Long id);
    public User edit_User(User user , Long id);
}
