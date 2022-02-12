package br.com.alura.ecommerce;

import br.com.alura.ecommerce.model.Email;
import br.com.alura.ecommerce.model.Order;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var dispatcher = new KafkaDispatcher<Order>()) {
            try (var emailDispatcher = new KafkaDispatcher<Email>()) {
                for (int i = 0; i < 10; i++) {
                    var userId = UUID.randomUUID().toString();
                    var orderId = UUID.randomUUID().toString();
                    var amount = new BigDecimal(Math.random() * 5000 + 1);

                    var order = new Order(userId, orderId, amount);

                    dispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                    Email email = new Email("antonio.aguiar@gmail.com", "Tank you for your order, we are processing your request!");
                    emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
                }
            }
        }
    }

}