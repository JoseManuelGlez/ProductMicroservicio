package com.example.productmicroservicio.services.interfaces;

import com.example.productmicroservicio.persistances.entities.Product;
import com.example.productmicroservicio.web.dtos.requests.*;
import com.example.productmicroservicio.web.dtos.responses.BaseResponse;

public interface IProductService {
    BaseResponse create(CreateProductRequest request);

    BaseResponse fillOut(CreateFillOutRequest request);

    BaseResponse list(CreateListRequest request);

    BaseResponse findById(CreateFindByIdRequest request);

    BaseResponse delete(CreateFindByIdRequest request);

    BaseResponse buy(CreateBuyRequest request);
}
