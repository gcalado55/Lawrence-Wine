package com.wine.Services;

import com.wine.Domain.Client.Client;
import com.wine.Repositories.ClientRepository;
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
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client findClientById(String clientId){
        Optional<Client> client = clientRepository.findById(clientId);
        return client.orElseThrow(() -> new ResourceNotFoundException(clientId));
    }

    public Client addClient(Client client){
        return clientRepository.save(client);
    }

    public void deleteClient(String clientId) {
        try{
            clientRepository.deleteById(clientId);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(clientId);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Client updateClient(String clientId, Client client){
        try {
            Client newClient = clientRepository.getReferenceById(clientId);
            editData(newClient, client);
            return clientRepository.save(newClient);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(clientId);
        }
    }

    public void editData(Client newClient, Client client){
        newClient.setId(client.getId());
        newClient.setName(client.getName());
        newClient.setAddress(client.getAddress());
        newClient.setPhone(client.getPhone());
        newClient.setEmail(client.getEmail());
    }
}
