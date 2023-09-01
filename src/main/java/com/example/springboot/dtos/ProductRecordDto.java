package com.example.springboot.dtos;

import com.example.springboot.models.CategoryModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;

public record ProductRecordDto(String name, BigDecimal value, String description, String imgUrl, Set<CategoryModel> categories) {

}
