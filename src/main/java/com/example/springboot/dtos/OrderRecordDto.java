package com.example.springboot.dtos;

import com.example.springboot.models.OrderItemModel;
import com.example.springboot.models.User;
import com.example.springboot.models.enums.OrderStatus;

import java.time.Instant;
import java.util.List;

public record OrderRecordDto(Instant moment, OrderStatus orderStatus, User client, List<OrderItemModel> items) {
}
