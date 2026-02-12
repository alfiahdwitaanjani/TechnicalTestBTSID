package com.technicaltes.tesbts.id.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double price;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String category;

    // Simpan list string di tabel terpisah
    @ElementCollection
    @CollectionTable(name = "product_images", 
                     joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images;

    private LocalDateTime createdAt;
    private String createdBy;
    private Long createdById;

    private LocalDateTime updatedAt;
    private String updatedBy;
    private Long updatedById;
}
