package com.example.productmicroservicio.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateFillOutRequest {
    private Double amount;
    private String sessionId;
}
