package com.example.productmicroservicio.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBuyRequest {
    private Long productId;
    private Long amount;
    private String sessionId;
}
