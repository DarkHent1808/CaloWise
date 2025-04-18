package com.javaweb.demosb.service;

import com.javaweb.demosb.dto.CustomUserDetails;
import com.javaweb.demosb.entity.UserEntity;
import com.javaweb.demosb.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUserName(username);
        if (user.isPresent()) {
            return new CustomUserDetails(user.get());
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
