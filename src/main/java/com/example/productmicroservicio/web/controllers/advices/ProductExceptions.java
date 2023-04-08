package com.example.productmicroservicio.web.controllers.advices;

import com.example.productmicroservicio.web.dtos.responses.BaseResponse;
import com.example.productmicroservicio.web.rabbit.IRabbitMQ;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductExceptions {
    @Autowired
    private IRabbitMQ rabbitMQ;

    public void createError(Exception e, String sessionId) throws JsonProcessingException {
        BaseResponse response = BaseResponse.builder()
                .sessionId(sessionId)
                .message(e.getMessage())
                .data("No fue posible agregar el producto")
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(412)
                .build();
        rabbitMQ.sendToProductCreateErrorQueue(response);
        throw new AmqpRejectAndDontRequeueException("error");
    }

    public void buyError(Exception e, String sessionId) throws JsonProcessingException {
        BaseResponse response = BaseResponse.builder()
                .sessionId(sessionId)
                .message(e.getMessage())
                .data("No fue posible comprar el producto")
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(412)
                .build();
        rabbitMQ.sendToProductBuyErrorQueue(response);
        throw new AmqpRejectAndDontRequeueException("error");
    }

    public void findByIdError(Exception e, String sessionId) throws JsonProcessingException{
        BaseResponse response = BaseResponse.builder()
                .sessionId(sessionId)
                .message(e.getMessage())
                .data("No fue posible encontrar el producto")
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(412)
                .build();
        rabbitMQ.sendToProductFindByIdError(response);
        throw new AmqpRejectAndDontRequeueException("error");
    }
}
