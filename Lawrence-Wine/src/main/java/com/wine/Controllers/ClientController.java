package com.wine.Controllers;

import com.wine.Domain.Client.Client;
import com.wine.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> listClients(){
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findClientById(@PathVariable String clientId){
        Client client = clientService.findClientById(clientId);
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String clientId){
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable String clientId, @RequestBody Client client){
        client = clientService.updateClient(clientId, client);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    public ResponseEntity<Client> addClient (@RequestBody Client client){
        Client newClient = clientService.addClient(client);
        return ResponseEntity.ok().body(newClient);
    }
}
