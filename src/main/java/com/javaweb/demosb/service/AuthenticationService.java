package com.javaweb.demosb.service;

import com.javaweb.demosb.dto.LoginRequestDTO;
import com.javaweb.demosb.dto.RegisterRequestDTO;
import com.javaweb.demosb.dto.ResponseDTO;
import com.javaweb.demosb.entity.UserEntity;
import com.javaweb.demosb.exception.AuthException;
import com.javaweb.demosb.repository.UserRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Objects;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public AuthenticationService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ResponseDTO<String> registerUser(RegisterRequestDTO registerRequestDTO){
        if (userRepository.findByUserName(registerRequestDTO.getUserName()).isPresent()) {
            throw new AuthException("User already exists");
        }
        if (userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) {
            throw new AuthException("Email already exists");
        }
        UserEntity user = modelMapper.map(registerRequestDTO, UserEntity.class);
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        String accessToken = jwtService.generateToken(user);
        return new ResponseDTO<>("Success", accessToken);
    }


    public ResponseDTO<String> loginUser(@Valid LoginRequestDTO loginRequestDTO){
        UserEntity entity = userRepository.findByUserName(loginRequestDTO.getUserName())
                .orElseThrow(() -> new AuthException("User not found"));
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUserName(),
                            loginRequestDTO.getPassword()
                    ));
        } catch (Exception e) {
            throw new AuthException("Invalid username or password");
        }
        String accessToken = jwtService.generateToken(entity);
        return new ResponseDTO<>("Success", accessToken);
    }
}
