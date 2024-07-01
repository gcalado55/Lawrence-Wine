package com.wine.Domain.OrderedItem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wine.Domain.Order.Order;
import com.wine.Domain.Wine.Wine;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "ordered_itens")
public class OrderedItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private Integer itemQuantity;

}
