package com.example.demo.feature01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired private ProductSearchService searchService;

    @GetMapping("/product/{id}")
    public ResponseProduct getProduct(@PathVariable int id) {
        return searchService.searchById(id);
    }

    @GetMapping("/product/search")
    public ResponseProduct getProduct(@RequestParam @Nullable Integer id, @RequestParam @Nullable String name) {
        /*
         Experiment to see what data is captured by Spring from different set of query string.
         Result: query string VS value of parameter.
            /product/search                      -> id=null, name=null
            /product/search?id=2                 -> id=2   , name=null
            /product/search?name=product 2       -> id=null, name="product 2"
            /product/search?id=2&name=product 2  -> id=2   , name="product 2"
        */
        ResponseProduct res = new ResponseProduct();
        res.setId(id == null ? -1 : id);
        res.setName(name);
        return res;
    }
}
