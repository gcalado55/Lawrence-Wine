package com.wine.Config;

import com.wine.Domain.Client.Client;
import com.wine.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {

        Client c1 = new Client(null, "Gabriel Calado", "Rua santa Lúcia 365", "gcalado@gmail.com", "81996959564");
        Client c2 = new Client(null, "Mirella Monteiro", "Rua hermano de barros 6120", "mmonteiro@gmail.com", "81999998888");

        clientRepository.saveAll(Arrays.asList(c1,c2));
    }
}
