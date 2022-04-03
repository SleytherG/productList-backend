package com.productsapp.products.management.service;

import com.productsapp.products.management.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductManagementService {

    List<ProductDTO> productList();

    Boolean addProduct (ProductDTO product );

    Boolean editProduct (String id, ProductDTO product );

    Boolean delete(String id);

//    ProductDTO getProduct(String id);
}
