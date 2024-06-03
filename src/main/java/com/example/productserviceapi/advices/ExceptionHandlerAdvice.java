package com.example.productserviceapi.advices;

import com.example.productserviceapi.dtos.ExceptionDto;
import com.example.productserviceapi.exceptions.ProductLimitExceedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @org.springframework.web.bind.annotation.ExceptionHandler(ProductLimitExceedException.class)
    public ResponseEntity<ExceptionDto> handleProductException()
    {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setExceptionCode("PRODUCT_LIMIT_REACHED");
        exceptionDto.setExceptionMessage("Custom Exception Handling");
        exceptionDto.setExceptionDate(new Date());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDto> handleException()
    {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setExceptionCode("UNKNOWN_ERROR");
        exceptionDto.setExceptionMessage("Something went wrong");
        exceptionDto.setExceptionDate(new Date());
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
