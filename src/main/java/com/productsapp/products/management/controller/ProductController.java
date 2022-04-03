package com.productsapp.products.management.controller;

import com.productsapp.products.management.dto.ProductDTO;
import com.productsapp.products.management.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductManagementService productService;

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping(value = "/greet/{name}")
    public String greet(@PathVariable(value = "name") String name ) {
        return "Hello " + name + " from SpringBoot";
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping(value = "/productList")
    public ResponseEntity productList() {
        return new ResponseEntity(productService.productList(), HttpStatus.OK );
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @GetMapping(value = "/{id}/getProduct")
    public ResponseEntity getProduct(@PathVariable(value = "id") String id ) {
        return new ResponseEntity( null, HttpStatus.OK );
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping(value = "/addProduct")
    public ResponseEntity addProduct(@RequestBody ProductDTO product ) {
        return new ResponseEntity( productService.addProduct( product ) , HttpStatus.OK );
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @PutMapping(value = "/editProduct/{id}")
    public ResponseEntity editProduct (@PathVariable(value = "id") String id, @RequestBody ProductDTO product ) {
        return new ResponseEntity(productService.editProduct(id, product), HttpStatus.OK );
    }

    @CrossOrigin(origins = "http://localhost:5000")
    @DeleteMapping(value = "/deleteProduct/{id}")
    public ResponseEntity deleteProduct (@PathVariable(value = "id") String id ) {
        return new ResponseEntity(productService.delete( id ), HttpStatus.OK );
    }
}
