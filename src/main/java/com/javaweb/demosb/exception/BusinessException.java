package com.javaweb.demosb.exception;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessException extends RuntimeException {
    private String message;
}
