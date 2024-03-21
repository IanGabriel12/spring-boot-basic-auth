package com.example.auth.auth;

import javax.management.InvalidAttributeValueException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.dtos.SignUpDto;
import com.example.auth.user.User;
import com.example.auth.user.UserRepository;

@Service
public class AuthService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByLogin(username);
        return user;
    }

    public UserDetails signUp(SignUpDto data) throws InvalidAttributeValueException {
        if(userRepository.findByLogin(data.login()) != null) {
            throw new InvalidAttributeValueException("Username already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        return userRepository.save(newUser);
    }

}
