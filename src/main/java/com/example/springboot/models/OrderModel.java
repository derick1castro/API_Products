package com.example.springboot.models;

import com.example.springboot.models.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel extends RepresentationModel<OrderModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idOrder;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @PreUpdate
    @PrePersist
    private void beforePersist() {
        this.moment = Instant.now();
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserModel client;

    @OneToMany(mappedBy = "id.orderModel")
    private List<OrderItemModel> items = new ArrayList<>();

}
