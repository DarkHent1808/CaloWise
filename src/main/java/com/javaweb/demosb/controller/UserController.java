package com.javaweb.demosb.controller;

import com.javaweb.demosb.dto.UserDTO;
import com.javaweb.demosb.entity.UserEntity;
import com.javaweb.demosb.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/{userName}")
    public ResponseEntity<?> getUserByUserName(@PathVariable(value = "userName") String userName){
        return ResponseEntity.ok(userService.getUserByUserName(userName));
    }

    @GetMapping(value="/me")
    public ResponseEntity<?> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PostMapping("/save_user")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO dto){
        return ResponseEntity.ok(userService.saveUser(dto));
    }
}
