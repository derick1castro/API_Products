package com.example.springboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "TB_PRODUCTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel extends RepresentationModel<ProductModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    private String name;
    private String description;
    private BigDecimal value;
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "TB_PRODUCT_CATEGORY", joinColumns = @JoinColumn(name = "PRODUCT_ID"), inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private List<CategoryModel> categories = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderItemModel> items = new ArrayList<>();

    public List<Order> getOrders(){
        List<Order> set = new ArrayList<>();
        for (OrderItemModel x : items) {
            set.add(x.getOrderModel());
        }
        return set;
    }

}
