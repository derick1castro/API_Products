package com.example.springboot.models;

import com.example.springboot.models.pk.OrderItemPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "TB_ORDER_ITEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @JsonIgnore
    private OrderItemPk id = new OrderItemPk();

    private Integer quantity;
    private Double value;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductModel product;

    public Order getOrderModel() {
        return getOrderModel();
    }

    @JsonIgnore
    public ProductModel getProduct() {
        return getProduct();
    }
}
