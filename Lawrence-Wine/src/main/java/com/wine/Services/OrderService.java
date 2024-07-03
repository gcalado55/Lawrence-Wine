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

    public Order findOrderById(String id){
        Optional<Order> orderObj = orderRepository.findById(id);
        return orderObj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Order addOrder(Order obj){
        return orderRepository.save(obj);
    }

    public void deleteOrder(String id){
        try{
            orderRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Order UpdateOrder(String id, Order obj){
        try {
            Order entity = orderRepository.getReferenceById(id);
            editData(entity, obj);
            return orderRepository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void editData(Order entity, Order obj){
        entity.setId(obj.getId());
        entity.setDate(obj.getDate());
        entity.setOrderStatus(obj.getOrderStatus());
        entity.setClient(obj.getClient());
    }


}
