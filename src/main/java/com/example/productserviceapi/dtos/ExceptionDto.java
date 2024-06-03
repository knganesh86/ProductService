package com.example.productserviceapi.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionDto {
    String exceptionCode;
    String exceptionMessage;
    Date exceptionDate;
}
