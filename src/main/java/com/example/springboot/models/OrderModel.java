package com.example.springboot.models;

import com.example.springboot.models.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "TB_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idOrder;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @PrePersist
    private void beforePersist() {
        // Define o valor do campo "momento" como o instante atual antes de persistir no banco de dados.
        this.moment = Instant.now();
    }

    @ManyToOne
    @JoinColumn(name = "client_id")
    private UserModel client;

}
