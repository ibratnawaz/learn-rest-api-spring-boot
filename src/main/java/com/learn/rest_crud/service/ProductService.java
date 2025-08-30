package com.learn.rest_crud.service;

import com.learn.rest_crud.exception.ResourceNotFoundException;
import com.learn.rest_crud.model.Product;
import com.learn.rest_crud.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Data Found with id " + id));
    }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public void removeProduct(int id) {
        productRepo.delete(getProductById(id));
    }
}
