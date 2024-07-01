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
@RequestMapping(value = "/wine")
public class WineController {

    @Autowired
    private WineService wineService;

    @GetMapping
    public ResponseEntity<List<Wine>> findAll(){
        List<Wine> wines = wineService.findAll();
        return ResponseEntity.ok().body(wines);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Wine> findById(@PathVariable String id){
        Wine wineObj = wineService.findById(id);
        return ResponseEntity.ok().body(wineObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        wineService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Wine> edit(@PathVariable String id, @RequestBody Wine obj){
        obj = wineService.edit(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Wine> register(@RequestBody Wine obj){
        obj = wineService.register(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.ok().body(obj);
    }
}
