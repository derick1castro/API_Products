package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String name, @NotBlank String email, String phone, @NotBlank String password) {
}
