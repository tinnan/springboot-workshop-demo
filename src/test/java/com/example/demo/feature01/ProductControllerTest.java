package com.example.demo.feature01;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest extends AbstractControllerTest {
    @Autowired private MockMvc mvc;

    @MockBean private ProductSearchService searchService;

    @Test
    public void testGetProduct() throws Exception {
        // Arrange.
        String inputUrl = "/product/1";
        ResponseProduct mockResponse = new ResponseProduct();
        mockResponse.setId(1);
        mockResponse.setName("Product 1");
        given(this.searchService.searchById(1))
                .willReturn(mockResponse);

        // Action.
        this.mvc.perform(get(inputUrl))

        // Assert.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product 1")));

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
