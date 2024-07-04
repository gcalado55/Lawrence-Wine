package com.wine.Controllers;

import com.wine.Domain.Client.Client;
import com.wine.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> listClients(){
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping(value = "/{clientId}")
    public ResponseEntity<Client> findClientById(@PathVariable String clientId){
        Client client = clientService.findClientById(clientId);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable String clientId){
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping(value = "/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable String clientId, @RequestBody Client client){
        client = clientService.updateClient(clientId, client);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> addClient (@RequestBody Client client){
        Client newClient = clientService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }
}
