package com.wine.Domain.Wine;

import com.wine.Domain.Order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "wines")
public class Wine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String harvest;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Wine(String id, String brand, String harvest, String description, Double price, Integer stock) {
        this.id = id;
        this.brand = brand;
        this.harvest = harvest;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Order getOrder() {
        return order;
    }
}
