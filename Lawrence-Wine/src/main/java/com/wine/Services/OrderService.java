package com.wine.Services;

import com.wine.Domain.Order.Order;
import com.wine.Repositories.OrderRepository;
import com.wine.Services.Exceptions.DatabaseException;
import com.wine.Services.Exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order findOrderById(String orderId){
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElseThrow(() -> new ResourceNotFoundException(orderId));
    }

    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    public void deleteOrder(String orderId){
        try{
            orderRepository.deleteById(orderId);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(orderId);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Order UpdateOrder(String orderId, Order order){
        try {
            Order newOrder = orderRepository.getReferenceById(orderId);
            editData(newOrder, order);
            return orderRepository.save(newOrder);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(orderId);
        }
    }

    public void editData(Order newOrder, Order order){
        newOrder.setId(order.getId());
        newOrder.setDate(order.getDate());
        newOrder.setOrderStatus(order.getOrderStatus());
        newOrder.setClient(order.getClient());
    }


}
