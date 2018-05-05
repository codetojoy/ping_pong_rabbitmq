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
        Ball ball = new Ball(args[1]);
        String message = ball.toJSON();
        String routingKey = Application.routingKey();
        System.out.println("TRACER Sending message to " + routingKey);
        rabbitTemplate.convertAndSend(Application.topicExchangeName, routingKey, message);
    }
}
