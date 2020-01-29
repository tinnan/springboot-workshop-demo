package com.example.demo.feature01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired private MockMvc mvc;

    @MockBean private ProductRepository mockProductRepo;

    @Test
    public void testGetProduct() throws Exception {
        String inputUrl = "/product/1";
        int expHttpCode = 200;
        String expResponse = "{ id:1, name: 'Product 1' }";

        // Mock.
        given(this.mockProductRepo.findById(1))
                .willReturn(new Product(1, "Product 1"));

        // Action + Assert.
        this.mvc.perform(get(inputUrl))
                .andExpect(status().is(expHttpCode))
                .andExpect(content().json(expResponse));
    }

    @Test
    public void testQueryProductByNothing() throws Exception {
        this.mvc.perform(get("/product/search"))
                .andExpect(status().isOk())
                .andExpect(content().json("{ id: -1, name: null }"));
    }

    @Test
    public void testQueryProductById() throws Exception {
        this.mvc.perform(get("/product/search?id=2"))
                .andExpect(status().isOk())
                .andExpect(content().json("{ id: 2, name: null }"));
    }

    @Test
    public void testQueryProductByName() throws Exception {
        this.mvc.perform(get("/product/search?name=product 2"))
                .andExpect(status().isOk())
                .andExpect(content().json("{ id: -1, name: 'product 2' }"));
    }

    @Test
    public void testQueryProductByIdAndName() throws Exception {
        this.mvc.perform(get("/product/search?id=2&name=product 2"))
                .andExpect(status().isOk())
                .andExpect(content().json("{ id: 2, name: 'product 2' }"));
    }
}
