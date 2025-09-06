package com.learn.rest_crud.service;

import com.learn.rest_crud.exception.ResourceNotFoundException;
import com.learn.rest_crud.model.Product;
import com.learn.rest_crud.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        log.info("ProductService::getAllProducts called!");
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Data Found with id " + id));
    }

    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Product productData, int id) {
        Product product = getProductById(id);

        product.setName(productData.getName());
        product.setCategory(productData.getCategory());
        product.setPrice(productData.getPrice());
        product.setProductAvailable(productData.isProductAvailable());
        product.setStockQuantity(productData.getStockQuantity());

        return productRepo.save(product);
    }

    public void removeProduct(int id) {
        log.warn("ProductService::removeProduct called for id " + id);
        Product product = getProductById(id);
        productRepo.delete(product);
    }
}
