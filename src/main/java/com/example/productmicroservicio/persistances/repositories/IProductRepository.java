package com.example.productmicroservicio.persistances.repositories;

import com.example.productmicroservicio.persistances.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository <Product, Long> {

}
