package com.example.demo.feature01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductApiIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetProductId2ShouldReturnProduct2() throws Exception {
        // Arrange.
        productRepository.save(new Product(1, "Integrate Product 1"));
        productRepository.save(new Product(2, "Integrate Product 2"));

        // Act.
        ResponseEntity<ResponseProduct> response =
                restTemplate.getForEntity("/product/2", ResponseProduct.class);

        // Assert.
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().getId());
        assertEquals("Integrate Product 2", response.getBody().getName());
    }
}
