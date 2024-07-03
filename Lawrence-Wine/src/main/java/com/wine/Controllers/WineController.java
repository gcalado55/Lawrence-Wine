package com.wine.Controllers;

import com.wine.Domain.Wine.Wine;
import com.wine.Services.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/wines")
public class WineController {

    @Autowired
    private WineService wineService;

    @GetMapping
    public ResponseEntity<List<Wine>> listWines(){
        List<Wine> wines = wineService.getAllWines();
        return ResponseEntity.ok().body(wines);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Wine> findWineById(@PathVariable String wineId){
        Wine wineObj = wineService.findWineById(wineId);
        return ResponseEntity.ok().body(wineObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteWine(@PathVariable String wineId){
        wineService.deleteWine(wineId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Wine> updateWine(@PathVariable String wineId, @RequestBody Wine wine){
        wine = wineService.updateWine(wineId, wine);
        return ResponseEntity.ok().body(wine);
    }

    @PostMapping
    public ResponseEntity<Wine> addWine(@RequestBody Wine wine){
        Wine newWine = wineService.addWine(wine);
        return ResponseEntity.ok().body(newWine);
    }
}
