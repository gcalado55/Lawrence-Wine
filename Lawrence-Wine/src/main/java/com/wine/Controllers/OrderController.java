package com.wine.Controllers;

import com.wine.Domain.Order.Order;
import com.wine.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> listOrders(){
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable String orderId){
        Order orderObj = orderService.findOrderById(orderId);
        return ResponseEntity.ok().body(orderObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody Order order){
        order = orderService.UpdateOrder(orderId, order);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        Order newOrder = orderService.addOrder(order);
        return ResponseEntity.ok().body(newOrder);
    }
}
