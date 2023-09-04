package com.example.springboot.dtos;

import com.example.springboot.models.OrderItemModel;
import com.example.springboot.models.UserModel;
import com.example.springboot.models.enums.OrderStatus;

import java.time.Instant;
import java.util.Set;

public record OrderRecordDto(Instant moment, OrderStatus orderStatus, UserModel client, Set<OrderItemModel> items) {
}
