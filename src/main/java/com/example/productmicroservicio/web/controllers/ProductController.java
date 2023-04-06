package com.example.productmicroservicio.web.controllers;

import com.example.productmicroservicio.services.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {
    @Autowired
    private IProductService service;


}
