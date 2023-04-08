package com.example.productmicroservicio.web.controllers;

import com.example.productmicroservicio.services.interfaces.IProductService;
import com.example.productmicroservicio.utilities.MapperUtil;
import com.example.productmicroservicio.web.dtos.rabbit.IRabbitMQ;
import com.example.productmicroservicio.web.dtos.requests.*;
import com.example.productmicroservicio.web.dtos.responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {
    @Autowired
    private IProductService service;

    @Autowired
    private IRabbitMQ rabbitMQ;

    @RabbitListener(queues = "queue.products_create")
    public void create(String payload) throws JsonProcessingException {
        CreateProductRequest request = MapperUtil.deserialize(payload, CreateProductRequest.class);
        BaseResponse response = service.create(request);
        rabbitMQ//metodo
    }

    @RabbitListener(queues = "queue.products_fill_out")
    public void fillOut(String payload) throws JsonProcessingException {
        CreateFillOutRequest request = MapperUtil.deserialize(payload, CreateFillOutRequest.class);
        BaseResponse response = service.fillOut(request);
        rabbitMQ//metodo
    }

    @RabbitListener(queues = "queue.products_buy")
    public void list(String payload) throws JsonProcessingException {
        CreateBuyRequest request = MapperUtil.deserialize(payload, CreateBuyRequest.class);
        BaseResponse response = service.buy(request);
        rabbitMQ//metodo
    }

    @RabbitListener(queues = "queue.products_list")
    public void buy(String payload) throws JsonProcessingException {
        CreateListRequest request = MapperUtil.deserialize(payload, CreateListRequest.class);
        BaseResponse response = service.list(request);
        rabbitMQ//metodo
    }

    @RabbitListener(queues = "queue.products_find_one_by_id")
    public void findById(String payload) throws JsonProcessingException {
        CreateFindByIdRequest request = MapperUtil.deserialize(payload, CreateFindByIdRequest.class);
        BaseResponse response = service.findById(request);
        rabbitMQ//metodo
    }

    @RabbitListener(queues = "queue.products_delete")
    public void delete(String payload) throws JsonProcessingException {
        CreateFindByIdRequest request = MapperUtil.deserialize(payload, CreateFindByIdRequest.class);
        BaseResponse response = service.delete(request);
        rabbitMQ//metodo
    }
}
