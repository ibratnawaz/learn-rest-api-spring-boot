package com.learn.rest_crud.controller;

import com.learn.rest_crud.model.Product;
import com.learn.rest_crud.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    // Using lombok to auto create the constructor;
    /*
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    */


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // handling exception using try-catch is not preferred ❌ in enterprise level applications
    // best to use global level exception handling using @RestControllerAdvice ✅
    /*
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            System.out.println("here it is!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with id: " + id);
        }
    }
    */
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/product")
    public ResponseEntity<Product> addNewProduct(@Valid @RequestBody Product newProduct) {
        Product product = productService.createProduct(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable int id) {
        return ResponseEntity.status((HttpStatus.OK)).body(productService.updateProduct(product, id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.removeProduct(id);
        return ResponseEntity.noContent().build();
    }
}
