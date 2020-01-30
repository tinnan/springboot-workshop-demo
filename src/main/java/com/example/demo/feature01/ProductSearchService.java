package com.example.demo.feature01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductSearchService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseProduct searchById(int id) {
        Optional<Product> product = productRepository.findById(id);
        ResponseProduct response = new ResponseProduct();
        if (product.isPresent()) {
            response.setId(product.get().getId());
            response.setName(product.get().getName());
        }
        return response;
    }

}
