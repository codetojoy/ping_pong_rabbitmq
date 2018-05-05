package net.codetojoy.client;

import net.codetojoy.common.Ball;
import net.codetojoy.common.Constants;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sender implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String name = Application.name();
        String routingKey = Application.routingKey();
        Ball ball = new Ball(name);
        String message = ball.toJSON();
        System.out.println("TRACER Sending seed message to " + routingKey);
        rabbitTemplate.convertAndSend(Application.topicExchangeName, routingKey, message);
    }
}
