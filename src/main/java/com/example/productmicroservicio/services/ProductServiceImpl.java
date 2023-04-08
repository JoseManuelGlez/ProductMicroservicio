package com.example.productmicroservicio.services;

import com.example.productmicroservicio.persistances.entities.Product;
import com.example.productmicroservicio.persistances.repositories.IProductRepository;
import com.example.productmicroservicio.services.interfaces.IProductService;
import com.example.productmicroservicio.web.dtos.requests.*;
import com.example.productmicroservicio.web.dtos.responses.BaseResponse;
import com.example.productmicroservicio.web.dtos.responses.BuyResponse;
import com.example.productmicroservicio.web.dtos.responses.CreateProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRepository repository;

    @Override
    public BaseResponse create(CreateProductRequest request) {
        Product product = repository.save(from(request));

        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(product)
                .message("The product was saved")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public BaseResponse fillOut(CreateFillOutRequest request) {
        Product product = findAndEnsureExist(request.getProductId());
        long newTotal = request.getAmount() + product.getAmount();
        product.setAmount(newTotal);

        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(product)
                .message("The product was filled out")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public BaseResponse buy(CreateBuyRequest request) {
        Product product = findAndEnsureExist(request.getProductId());
        long newTotal = product.getAmount() - request.getAmount();
        product.setAmount(newTotal);
        double cost = product.getPrice() * request.getAmount();

        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(ticket(product, request.getAmount(), cost))
                .message("The product was bought")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private BuyResponse ticket(Product product, Long request, double cost) {
        BuyResponse response = new BuyResponse();
        response.setAmount(request);
        response.setPrice(cost);
        response.setProduct(product);
        return response;
    }

    @Override
    public BaseResponse list(CreateListRequest request) {
        Object object = repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(object)
                .message("The products was found")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public BaseResponse findById(CreateFindByIdRequest request) {
        Product product = findAndEnsureExist(request.getProductId());

        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(product)
                .message("The product was bought")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.FOUND)
                .statusCode(HttpStatus.FOUND.value())
                .build();
    }

    @Override
    public BaseResponse delete(CreateFindByIdRequest request) {
        repository.delete(findAndEnsureExist(request.getProductId()));

        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(null)
                .message("The product was eliminated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private Product from(CreateProductRequest request){
        Product product = new Product();

        product.setAmount(request.getAmount());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setName(request.getName());
        return product;
    }

    private CreateProductResponse from(Product product){
        CreateProductResponse response = new CreateProductResponse();

        response.setAmount(product.getAmount());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setName(product.getName());
        return response;
    }

    private Product findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
