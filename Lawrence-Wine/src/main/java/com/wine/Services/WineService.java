package com.wine.Services;

import com.wine.Domain.Wine.Wine;
import com.wine.Repositories.WineRepository;
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
public class WineService {

    @Autowired
    private WineRepository wineRepository;

    public List<Wine> findAll(){
        return wineRepository.findAll();
    }

    public Wine findById(String id){
        Optional<Wine> wineObj = wineRepository.findById(id);
        return wineObj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Wine register(Wine obj){
        return wineRepository.save(obj);
    }

    public void delete(String id){
        try {
            wineRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Wine edit(String id, Wine obj){
        try {
            Wine entity = wineRepository.getReferenceById(id);
            editData(entity, obj);
            return wineRepository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void editData(Wine entity, Wine obj){
        entity.setId(obj.getId());
        entity.setBrand(obj.getBrand());
        entity.setHarvest(obj.getHarvest());
        entity.setDescription(obj.getDescription());
        entity.setPrice(obj.getPrice());
        entity.setStock(obj.getStock());
    }
}
