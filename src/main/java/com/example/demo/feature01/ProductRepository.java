package com.example.demo.feature01;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findAll();

    Product findById(int id);
}
