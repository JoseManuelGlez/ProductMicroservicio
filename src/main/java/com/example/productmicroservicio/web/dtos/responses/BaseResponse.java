package com.example.productmicroservicio.web.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder @Getter @Setter
public class BaseResponse {

    private Object data;

    private String message;

    private Boolean success;

    private HttpStatus httpStatus;

    private String sessionId;

    private Integer statusCode;
}
