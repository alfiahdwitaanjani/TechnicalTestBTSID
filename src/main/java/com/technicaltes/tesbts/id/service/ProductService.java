package com.technicaltes.tesbts.id.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.technicaltes.tesbts.id.dto.ProductRequest;
import com.technicaltes.tesbts.id.entity.Product;
import com.technicaltes.tesbts.id.exception.ResourceNotFoundException;
import com.technicaltes.tesbts.id.repository.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repository;

    // ===============================
    // GET ALL + SEARCH + PAGINATION
    // ===============================
    public Page<Product> getAll(String search, String category, int limit, int page) {

        Pageable pageable = PageRequest.of(page, limit);

        if (search != null && !search.isEmpty()) {
            return repository.findByTitleContainingIgnoreCase(search, pageable);
        }

        if (category != null && !category.isEmpty()) {
            return repository.findByCategory(category, pageable);
        }

        return repository.findAll(pageable);
    }

    // ===============================
    // GET BY ID
    // ===============================
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    // ===============================
    // CREATE
    // ===============================
    public Product create(ProductRequest request) {

        if (request.getImages() == null || request.getImages().isEmpty()) {
            throw new RuntimeException("At least one image is required");
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setImages(request.getImages());

        product.setCreatedAt(LocalDateTime.now());
        product.setCreatedBy(username);

        return repository.save(product);
    }

    // ===============================
    // UPDATE
    // ===============================
    public Product update(Long id, ProductRequest request) {

        Product product = getById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        product.setTitle(request.getTitle());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setImages(request.getImages());

        product.setUpdatedAt(LocalDateTime.now());
        product.setUpdatedBy(username);

        return repository.save(product);
    }

    // ===============================
    // DELETE
    // ===============================
    public void delete(Long id) {
        Product product = getById(id);
        repository.delete(product);
    }
}
