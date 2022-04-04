package com.productsapp.products.management.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.productsapp.products.management.dto.ProductDTO;
import com.productsapp.products.management.firebase.FirebaseInitializer;
import com.productsapp.products.management.service.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductManagementServiceImpl implements ProductManagementService {

    @Autowired
    private FirebaseInitializer firebase;

    @Override
    public List<ProductDTO> productList() {
        List<ProductDTO> response = new ArrayList<>();
        ProductDTO product;

        ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().orderBy("nombre", Query.Direction.ASCENDING).get();

        try {
            for (DocumentSnapshot documentSnapshot : querySnapshotApiFuture.get().getDocuments() ) {
                product = documentSnapshot.toObject(ProductDTO.class);
                product.setId( documentSnapshot.getId() );
                response.add( product );
            }
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean addProduct(ProductDTO product) {
        Map<String, Object> productData = getProductData(product);

        ApiFuture<WriteResult> writeResultApiFuture =  getCollection().document().create( productData );

        try {
            if ( writeResultApiFuture.get() != null) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;

        } catch ( Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }




    @Override
    public Boolean editProduct(String id, ProductDTO product) {
        Map<String, Object> productData = getProductData( product );
        ApiFuture<WriteResult> writeResultApiFuture =  getCollection().document( id ).set( productData );

        try {
            if ( writeResultApiFuture.get() != null) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Boolean delete(String id) {
        ApiFuture<WriteResult> writeResultApiFuture =  getCollection().document( id ).delete();

        try {
            if ( writeResultApiFuture.get() != null ) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        } catch( Exception e) {
            e.printStackTrace();

        }
        return null;
    }

//    @Override
//    public ProductDTO getProduct(String id) {
//        ApiFuture<WriteResult> writeResultApiFuture =  getCollection().getId( id );
//
//    }

    private CollectionReference getCollection() {
        return firebase.getFirestore().collection("product");
    }

    private Map<String, Object> getProductData(ProductDTO product) {
        Map<String, Object> productData = new HashMap<>();
        productData.put( "nombre", product.getNombre() );
        productData.put( "precio", product.getPrecio() );
        return productData;
    }

}
