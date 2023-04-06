package com.example.productmicroservicio.services;

import com.example.productmicroservicio.persistances.entities.Product;
import com.example.productmicroservicio.persistances.repositories.IProductRepository;
import com.example.productmicroservicio.services.interfaces.IProductService;
import com.example.productmicroservicio.web.dtos.requests.CreateProductRequest;
import com.example.productmicroservicio.web.dtos.responses.BaseResponse;
import com.example.productmicroservicio.web.dtos.responses.CreateProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public BaseResponse fillOut(Long id, CreateProductRequest request) {
        Product product = findAndEnsureExist(id);
        Long newTotal = request.getAmount() + product.getAmount();
        product.setAmount(newTotal);

        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(product)
                .message("The product was filled out")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public BaseResponse buy(Long id, CreateProductRequest request) {
        Product product = findAndEnsureExist(id);
        Long newTotal = product.getAmount() - request.getAmount();
        product.setAmount(newTotal);

        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(product)
                .message("The product was bought")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public List<CreateProductResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {
        return findAndEnsureExist(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
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
