package com.example.productmicroservicio.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateListRequest {
    private Long productId;
    private String sessionId;
}
