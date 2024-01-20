package com.parquimetro.controller.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class StardardError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}