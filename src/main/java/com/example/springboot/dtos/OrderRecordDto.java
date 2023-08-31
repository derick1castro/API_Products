package com.example.springboot.dtos;

import java.time.Instant;

public record OrderRecordDto(Instant moment, Integer orderStatus) {
}
