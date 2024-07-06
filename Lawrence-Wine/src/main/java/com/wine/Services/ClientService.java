package com.wine.Services;

import com.wine.Domain.Client.Client;
import com.wine.Repositories.ClientRepository;
import com.wine.Services.Exceptions.DatabaseException;
import com.wine.Services.Exceptions.ResourceNotFoundException;
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

    public Client updateClient(String clientId, Client clientUpdated) throws Exception {

            Optional<Client> clientOpt = clientRepository.findById(clientId);

            if(clientOpt.isPresent()){
                Client client = clientOpt.get();
                client.setAddress(clientUpdated.getAddress());
                client.setEmail(clientUpdated.getEmail());
                client.setName(clientUpdated.getEmail());
                client.setPhone(clientUpdated.getPhone());
                return clientRepository.save(client);
            }

            throw new Exception("Client Not Found With Id: " + clientId);
    }
}
