package com.wine.Config;

import com.wine.Domain.Client.Client;
import com.wine.Domain.Order.Order;
import com.wine.Domain.OrderStatus;
import com.wine.Repositories.ClientRepository;
import com.wine.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {



        Client c1 = new Client(null, "Gabriel Calado", "Rua santa Lúcia 365", "gcalado@gmail.com", "81996959564");
        Client c2 = new Client(null, "Mirella Monteiro", "Rua hermano de barros 6120", "mmonteiro@gmail.com", "81999998888");

        Order o1 = new Order(null, Instant.now(), OrderStatus.AWAITING_PAYMENT.toString(), c1);
        Order o2 = new Order(null, Instant.now(), OrderStatus.PAYMENT_CONFIRMED.toString(), c2);

        clientRepository.saveAll(Arrays.asList(c1,c2));
        orderRepository.saveAll(Arrays.asList(o1,o2));

    }
}
