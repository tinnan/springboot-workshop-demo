package com.example.demo.feature01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductSearchServiceTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    private ProductSearchService searchService = new ProductSearchService();

    @DisplayName("When search by id=1 should return data of product 1.")
    @Test
    void testSearchByIdShouldReturnSameId() {
        given(this.repository.findById(1))
                .willReturn(Optional.of(new Product(1, "This is Product 1")));

        ResponseProduct responseProduct = searchService.searchById(1);
        assertNotNull(responseProduct);
        assertEquals(responseProduct.getId(), 1);
        assertEquals(responseProduct.getName(), "This is Product 1");
    }

    @DisplayName("When search by id=0 should return empty object.")
    @Test
    void testSearchByIdZeroShouldReturnEmptyObject() {
        given(this.repository.findById(0))
                .willReturn(Optional.ofNullable(null));

        ResponseProduct responseProduct = searchService.searchById(0);
        assertNotNull(responseProduct);
        assertEquals(responseProduct.getId(), 0);
        assertNull(responseProduct.getName());
    }
}