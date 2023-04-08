package com.example.productmicroservicio.web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Double amount;
    private String sessionId;
    private byte[] image;
}
