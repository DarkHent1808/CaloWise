package com.javaweb.demosb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> implements Serializable {
    private String message;
    private T data; // T is a generic type
}
