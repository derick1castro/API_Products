package com.example.springboot.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "TB_CATEGORY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCategory;
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<ProductModel> products = new ArrayList<>();
}
