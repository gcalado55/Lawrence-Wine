package com.wine.Domain.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wine.Domain.Client.Client;
import com.wine.Domain.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@Entity
@Table(name = "orders")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(nullable = false)
    private Instant date;

    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    //private Set<OrderItem> items = new HashSet<>();
}
