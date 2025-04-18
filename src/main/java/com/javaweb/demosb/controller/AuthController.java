package com.javaweb.demosb.controller;

import com.javaweb.demosb.dto.LoginRequestDTO;
import com.javaweb.demosb.dto.RegisterRequestDTO;
import com.javaweb.demosb.service.AuthenticationService;
import com.javaweb.demosb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value="/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

//    @GetMapping(value="/public/hello")
//    public String welcome(){
//        return "Dit me chuck ko authorization";
//    }
//
//    @GetMapping(value="/afterLogin")
//    public String afterLogin(){
//        return "Dit me Chuck sau khi login success";
//    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequest) {

        return ResponseEntity.ok(authenticationService.registerUser(registerRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestDTO loginRequest){
        return ResponseEntity.ok(authenticationService.loginUser(loginRequest));

    }
}
