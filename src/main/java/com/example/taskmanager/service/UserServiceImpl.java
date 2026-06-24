package com.example.taskmanager.service;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.exception.UserNotFoundException;
import com.example.taskmanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Setter
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final PasswordEncoder encoder;


    @Override
    public User findByUsername(String username) {

       return userRepository.findByUsername(username).orElseThrow(
               ()-> new UserNotFoundException("User with username"+username+" not found")
       );

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new UserNotFoundException("User with email"+email+" not found")
        );
    }

//    @Override
//    public User findByUsernameAndPassword(String username, String password) {
//        User user=
//
//                userRepository.findByUsername(
//
//                        dto.getUsername()
//
//                );
//        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
//                ()-> new UserNotFoundException("User with username"+username+password+" not found")
//        );
//    }

    @Override
    public User create(User user) {
        user.setPassword(

                encoder.encode(

                        user.getPassword()

                )

        );
        user.setRole("ROLE_USER");

        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
       User updatedUser = userRepository.findById(user.getId()).orElseThrow(
               ()-> new UserNotFoundException("User with id"+user.getId()+" not found")
       );
       updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        return userRepository.save(updatedUser);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("User with id"+id+" not found")
        );
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User"+id+" not found"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
