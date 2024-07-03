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

    public Client findClientById(String id){
        Optional<Client> clientObj = clientRepository.findById(id);
        return clientObj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Client addClient(Client obj){
        return clientRepository.save(obj);
    }

    public void deleteClient(String id) {
        try{
            clientRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Client updateClient(String id, Client obj){
        try {
            Client entity = clientRepository.getReferenceById(id);
            editData(entity, obj);
            return clientRepository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void editData(Client entity, Client obj){
        entity.setId(obj.getId());
        entity.setName(obj.getName());
        entity.setAddress(obj.getAddress());
        entity.setPhone(obj.getPhone());
        entity.setEmail(obj.getEmail());
    }
}
