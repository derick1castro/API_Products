package com.example.springboot.dtos;

import com.example.springboot.models.ProductModel;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoryRecordDto(String name, List<ProductModel> products) {
}
