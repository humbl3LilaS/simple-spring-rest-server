package com.edelweiss.placeholder.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorCustom {
    private int statusCode;
    private String message;
}
