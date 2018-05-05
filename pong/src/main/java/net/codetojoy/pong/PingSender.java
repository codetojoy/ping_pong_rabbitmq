package net.codetojoy.pong;

import net.codetojoy.common.Ball;
import net.codetojoy.common.Constants;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PingSender {
    private final RabbitTemplate rabbitTemplate;

    public PingSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void ping(Ball ball) {
        String routingKey = Constants.PING_ROUTING_KEY;
        String message = ball.toJSON();
        rabbitTemplate.convertAndSend(Application.topicExchangeName, routingKey, message);
    }
}
