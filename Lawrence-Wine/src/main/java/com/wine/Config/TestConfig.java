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

import java.time.Instant;
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


        Wine w1 = new Wine(null, "Quinta do morgado", "2018", "horrível", 30.00, 10);
        Wine w2 = new Wine(null, "Carreteiro", "2019", "pior ainda", 40.00, 8);

        Client c1 = new Client(null, "Gabriel Calado", "Rua santa Lúcia 365", "gcalado@gmail.com", "81996959564");
        Client c2 = new Client(null, "Mirella Monteiro", "Rua hermano de barros 6120", "mmonteiro@gmail.com", "81999998888");

        Order o1 = new Order(null, Instant.now(), OrderStatus.AWAITING_PAYMENT.toString(), c1, w1.getOrder().getItems());
        Order o2 = new Order(null, Instant.now(), OrderStatus.PAYMENT_CONFIRMED.toString(), c2, w2.getOrder().getItems());

        wineRepository.saveAll(Arrays.asList(w1,w2));
        clientRepository.saveAll(Arrays.asList(c1,c2));
        orderRepository.saveAll(Arrays.asList(o1,o2));


    }
}
