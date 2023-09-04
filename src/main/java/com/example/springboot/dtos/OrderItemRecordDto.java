package com.example.springboot.dtos;

import com.example.springboot.models.ProductModel;
import jakarta.validation.constraints.NotBlank;

public record OrderItemRecordDto(Integer quantity, Double value, ProductModel productModel) {
}
