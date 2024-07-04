package com.wine.Domain.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wine.Domain.Client.Client;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(nullable = false)
    private LocalDateTime date;

    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String wineBrand;
}
