package com.example.springboot.models.pk;

import com.example.springboot.models.OrderModel;
import com.example.springboot.models.ProductModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderModel orderModel;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductModel productModel;

}
