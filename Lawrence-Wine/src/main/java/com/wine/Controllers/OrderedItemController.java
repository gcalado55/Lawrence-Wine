package com.wine.Controllers;

import com.wine.Domain.Order.Order;
import com.wine.Domain.OrderedItem.OrderedItem;
import com.wine.Services.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/ordered_item")
public class OrderedItemController {

    @Autowired
    private OrderedItemService orderedItemService;

    @GetMapping
    public ResponseEntity<List<OrderedItem>> findAll(){
        List<OrderedItem> orderedItems = orderedItemService.findAll();
        return ResponseEntity.ok().body(orderedItems);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderedItem> findById(@PathVariable String id){
        OrderedItem orderedItemObj = orderedItemService.findById(id);
        return ResponseEntity.ok().body(orderedItemObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        orderedItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderedItem> edit(@PathVariable String id, @RequestBody OrderedItem obj){
        obj = orderedItemService.edit(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<OrderedItem> register(@RequestBody OrderedItem obj){
        obj = orderedItemService.register(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.ok().body(obj);
    }

}
