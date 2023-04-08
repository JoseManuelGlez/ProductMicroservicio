package com.example.productmicroservicio.web.dtos.responses;

import com.example.productmicroservicio.persistances.entities.Product;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BuyResponse {
    private Product product;
    private Long amount;
    private Double price;
}
