package com.example.productmicroservicio.web.rabbit;

import com.example.productmicroservicio.web.dtos.responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IRabbitMQ {
    void sendToProductCreateResponseQueue(BaseResponse response) throws JsonProcessingException;

    void sendToProductCreateErrorQueue(BaseResponse response) throws JsonProcessingException;

    void sendToProductBuyResponseQueue(BaseResponse response) throws JsonProcessingException;

    void sendToProductBuyErrorQueue(BaseResponse response) throws JsonProcessingException;

    void sendToProductListResponse(BaseResponse response) throws JsonProcessingException;

    void sendToProductFindByIdResponse(BaseResponse response) throws JsonProcessingException;

    void sendToProductFindByIdError(BaseResponse response) throws JsonProcessingException;
}
