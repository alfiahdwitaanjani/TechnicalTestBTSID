package com.technicaltes.tesbts.id.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.technicaltes.tesbts.id.dto.ProductRequest;
import com.technicaltes.tesbts.id.entity.Product;
import com.technicaltes.tesbts.id.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // supaya bisa diakses dari domain lain
public class ProductController {

    private final ProductService productService;

    // ===============================
    // GET ALL PRODUCTS
    // ===============================
    @GetMapping
    public ResponseEntity<Page<Product>> getAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int page) {

        Page<Product> products = productService.getAll(search, category, limit, page);
        return ResponseEntity.ok(products);
    }

    // ===============================
    // GET PRODUCT BY ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(product);
    }

    // ===============================
    // CREATE PRODUCT
    // ===============================
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductRequest request) {
        Product product = productService.create(request);
        return ResponseEntity.status(201).body(product);
    }

    // ===============================
    // UPDATE PRODUCT
    // ===============================
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        Product product = productService.update(id, request);
        return ResponseEntity.ok(product);
    }

    // ===============================
    // DELETE PRODUCT
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
