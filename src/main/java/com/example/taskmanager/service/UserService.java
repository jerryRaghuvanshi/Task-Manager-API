package com.example.taskmanager.service;

import com.example.taskmanager.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
//    User findByUsernameAndPassword(String username, String password);
    User create(User user);
    User update(User user);
    void delete(Long id);
    User findById(Long id);
    List<User> findAll();
}
