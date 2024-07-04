package com.wine.Controllers;

import com.wine.Domain.Wine.Wine;
import com.wine.Services.WineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/wines")
@Slf4j
public class WineController {

    @Autowired
    private WineService wineService;

    @GetMapping
    public ResponseEntity<List<Wine>> listWines(){
        List<Wine> wines = wineService.getAllWines();
        return new ResponseEntity<>(wines, HttpStatus.OK);
    }

    @GetMapping(value = "/{wineId}")
    public ResponseEntity<Wine> findWineById(@PathVariable String wineId){
        Wine wine = wineService.findWineById(wineId);
        return new ResponseEntity<>(wine, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{wineId}")
    public ResponseEntity<Void> deleteWine(@PathVariable String wineId){
        wineService.deleteWine(wineId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{wineId}")
    public ResponseEntity<Wine> updateWine(@PathVariable String wineId, @RequestBody Wine wine){

        try {
            wine = wineService.updateWine(wineId, wine);
            return new ResponseEntity<>(wine, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Wine> addWine(@RequestBody Wine wine){
        Wine newWine = wineService.addWine(wine);
        return new ResponseEntity<>(newWine, HttpStatus.CREATED);
    }
}
