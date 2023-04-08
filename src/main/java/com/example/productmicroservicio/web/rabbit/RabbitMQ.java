package com.example.productmicroservicio.web.rabbit;

import com.example.productmicroservicio.utilities.MapperUtil;
import com.example.productmicroservicio.web.dtos.responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQ implements IRabbitMQ{

    @Autowired
    private RabbitTemplate template;

    @Override
    public void sendToProductCreateResponseQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.product_create_response");
    }

    @Override
    public void sendToProductCreateErrorQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.product_create_errors");
    }

    @Override
    public void sendToProductBuyResponseQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.product_buy_response");
    }

    @Override
    public void sendToProductBuyErrorQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.product_buy_errors");
    }

    @Override
    public void sendToProductListResponse(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.product_list_response");
    }

    @Override
    public void sendToProductFindByIdResponse(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.product_find_by_id_response", serialize);
    }

    @Override
    public void sendToProductFindByIdError(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.product_find_by_id_errors");
    }

}
