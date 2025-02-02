package com.wine.Domain.Wine;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "wines")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
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
}
