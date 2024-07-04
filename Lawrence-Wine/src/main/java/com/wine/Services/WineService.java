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

    public List<Wine> getAllWines(){
        return wineRepository.findAll();
    }

    public Wine findWineById(String wineId){
        Optional<Wine> wine = wineRepository.findById(wineId);
        return wine.orElseThrow(() -> new ResourceNotFoundException(wineId));
    }

    public Wine addWine(Wine wine){
        return wineRepository.save(wine);
    }

    public void deleteWine(String wineId){
        try {
            wineRepository.deleteById(wineId);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(wineId);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Wine updateWine(String wineId, Wine wine){
        try {
            Wine newWine = wineRepository.getReferenceById(wineId);
            editData(newWine, wine);
            return wineRepository.save(newWine);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(wineId);
        }
    }

    public void editData(Wine newWine, Wine wine){
        newWine.setId(wine.getId());
        newWine.setBrand(wine.getBrand());
        newWine.setHarvest(wine.getHarvest());
        newWine.setDescription(wine.getDescription());
        newWine.setPrice(wine.getPrice());
        newWine.setStock(wine.getStock());
    }
}
