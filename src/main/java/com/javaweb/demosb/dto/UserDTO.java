package com.javaweb.demosb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long userId;
    private String userName;
    private String password;
    private String email;
    private String role;
    private Date createdAt;
    private Integer weight;
    private Integer height;
    private Float bmi;
    private Integer age;
    private String gender;

    public UserDTO(Long userId, String userName, String email, String role, Date createdAt, Integer weight, Integer height, Float bmi, Integer age, String gender) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.age = age;
        this.gender = gender;
    }
}
