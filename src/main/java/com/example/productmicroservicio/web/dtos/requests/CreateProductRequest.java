package com.example.productmicroservicio.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateProductRequest {
    private String name;
    private Double price;
    private String description;
    private Long amount;
}
