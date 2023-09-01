package com.example.springboot.dtos;

import com.example.springboot.models.UserModel;
import com.example.springboot.models.enums.OrderStatus;

import java.time.Instant;

public record OrderRecordDto(Instant moment, OrderStatus orderStatus, UserModel client) {
}
