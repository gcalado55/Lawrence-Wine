package com.wine.Services;

import com.wine.Domain.OrderedItem.OrderedItem;
import com.wine.Repositories.OrderedItemRepository;
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
public class OrderedItemService {

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    public List<OrderedItem> findAll(){
        return orderedItemRepository.findAll();
    }

    public OrderedItem findById(String id){
        Optional<OrderedItem> orderedItemObj = orderedItemRepository.findById(id);
        return orderedItemObj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public OrderedItem register(OrderedItem obj){
        return orderedItemRepository.save(obj);
    }

    public void delete(String id){
        try {
            orderedItemRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public OrderedItem edit(String id, OrderedItem obj){
        try {
            OrderedItem entity = orderedItemRepository.getReferenceById(id);
            editData(entity, obj);
            return orderedItemRepository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void editData(OrderedItem entity, OrderedItem obj){
        entity.setId(obj.getId());
        entity.setItemQuantity(obj.getItemQuantity());
        entity.setOrder(obj.getOrder());
    }
}
