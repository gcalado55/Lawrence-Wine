package com.wine.Config;

import com.wine.Domain.Client.Client;
import com.wine.Domain.Order.Order;
import com.wine.Domain.OrderStatus;
import com.wine.Domain.Wine.Wine;
import com.wine.Repositories.ClientRepository;
import com.wine.Repositories.OrderRepository;
import com.wine.Repositories.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WineRepository wineRepository;

    @Override
    public void run(String... args) throws Exception {

        Wine w1 = new Wine(null, "Quinta do Morgado", "2018", "horrível", 30.00, 10);
        Wine w2 = new Wine(null, "Carreteiro", "2019", "pior ainda", 40.00, 8);

        // Save Wines to database
        wineRepository.saveAll(Arrays.asList(w1, w2));

        // Create Clients
        Client c1 = new Client(null, "Gabriel Calado", "Rua Santa Lúcia 365", "gcalado@gmail.com", "81996959564");
        Client c2 = new Client(null, "Mirella Monteiro", "Rua Hermano de Barros 6120", "mmonteiro@gmail.com", "81999998888");

        // Save Clients to database
        clientRepository.saveAll(Arrays.asList(c1, c2));

        // Create Orders and set wineBrand with the corresponding wine brand
        Order o1 = new Order(null, LocalDateTime.now(), OrderStatus.AWAITING_PAYMENT.toString(), c1);
        Order o2 = new Order(null, LocalDateTime.now(), OrderStatus.PAYMENT_CONFIRMED.toString(), c2);

        o1.setWineBrand(w1.getBrand());
        o2.setWineBrand(w2.getBrand());

        // Save Orders to database
        orderRepository.saveAll(Arrays.asList(o1, o2));
    }
}
