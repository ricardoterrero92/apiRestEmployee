package com.employeeapp.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EmployeeExceptions extends RuntimeException{

    private String message;

    private HttpStatus httpStatus;

    public EmployeeExceptions (String message, HttpStatus httpStatus) {
        super();
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
