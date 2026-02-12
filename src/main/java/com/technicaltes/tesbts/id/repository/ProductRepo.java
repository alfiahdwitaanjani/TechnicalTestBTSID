package com.technicaltes.tesbts.id.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.technicaltes.tesbts.id.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Page<Product> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Product> findByCategory(String category, Pageable pageable);

}
