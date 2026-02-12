package com.technicaltes.tesbts.id.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.util.List;

@Data
public class ProductRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    private List<String> images;
}
