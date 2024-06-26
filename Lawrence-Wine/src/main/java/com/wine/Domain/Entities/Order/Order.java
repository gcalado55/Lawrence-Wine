package com.wine.Domain.Entities.Order;

import com.wine.Domain.Entities.OrderStatus;
import com.wine.Domain.Entities.OrderItem.OrderedItem;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "tb_orders")
public class Order implements Serializable {
    private Long id;
    private Integer clientId;
    private Date date;
    private OrderStatus orderStatus;
    private List<OrderedItem> orderedItemList;
}
