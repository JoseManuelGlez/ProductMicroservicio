package com.example.productmicroservicio.services.interfaces;

import com.example.productmicroservicio.persistances.entities.Product;
import com.example.productmicroservicio.web.dtos.requests.CreateProductRequest;
import com.example.productmicroservicio.web.dtos.responses.BaseResponse;
import com.example.productmicroservicio.web.dtos.responses.CreateProductResponse;

import java.util.List;

public interface IProductService {
    BaseResponse create(CreateProductRequest request);

    BaseResponse fillOut(Long id, CreateProductRequest request);

    List<CreateProductResponse> list();

    Product findById(Long id);

    void delete(Long id);

    BaseResponse buy(Long id, CreateProductRequest request);
}
