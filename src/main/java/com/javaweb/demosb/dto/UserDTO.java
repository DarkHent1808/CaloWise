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
    private String email;
    private String role;
    private Date createdAt;
    private Integer weight;
    private Integer height;
    private Integer age;
    private String gender;
}
