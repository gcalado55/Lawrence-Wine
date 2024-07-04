package com.wine.Domain.Client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wine.Domain.Order.Order;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();
}
