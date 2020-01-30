package com.example.demo.feature01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
// This annotation is to fix problem where "Generated ID" is not reset after each test method.
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void findAllShouldListAllData() {
        // Arrange test data.
        this.repository.save(new Product(0, "Product 1"));
        this.repository.save(new Product(0, "product 2"));

        // Action.
        List<Product> products = this.repository.findAll();

        // Assert.
        assertThat(products.size()).isEqualTo(2);
        assertThat(products.get(0).getId()).isEqualTo(1);
        assertThat(products.get(0).getName()).isEqualTo("Product 1");
        assertThat(products.get(1).getId()).isEqualTo(2);
        assertThat(products.get(1).getName()).isEqualTo("product 2");
    }

    @Test
    void findByIdShouldListSelectedData() {
        // Arrange.
        Product savedProduct1 = this.repository.save(new Product(0, "Product 3"));
        Product savedProduct2 = this.repository.save(new Product(0, "Product 4"));

        // Action.
        Optional<Product> product = this.repository.findById(2);

        // Assert.
        assertTrue(product.isPresent());
        assertThat(product.get().getName()).isEqualTo("Product 4");
    }
}