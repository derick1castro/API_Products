package com.example.springboot.services;

import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductModel insert(ProductModel productModel){
        return productRepository.save(productModel);
    }

    public List<ProductModel> findAll(){
        return productRepository.findAll();
    }

    public ProductModel findById(UUID id){
        Optional<ProductModel> productO = productRepository.findById(id);
        return productO.get();
    }

    public void delete(UUID id){
        try {
            productRepository.deleteById(id);
        } catch (RuntimeException e){
            e.printStackTrace();
            throw e;
        }
    }

    public ProductModel update(UUID id, ProductModel productModel) {
        try {
            ProductModel productO = productRepository.getReferenceById(id);
            updateData(productO, productModel);
            return productRepository.save(productO);
        } catch (RuntimeException e) {
             e.printStackTrace();
             throw e;
        }
    }

    private void updateData(ProductModel productO, ProductModel productModel) {
        productO.setName(productModel.getName());
        productO.setValue(productModel.getValue());
        productO.setDescription(productModel.getDescription());
        productO.setImgUrl(productModel.getImgUrl());
    }
}
