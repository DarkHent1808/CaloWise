package com.javaweb.demosb.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthException extends RuntimeException {
    private String message;
}
