package net.codetojoy.ping;

import net.codetojoy.common.Ball;
import net.codetojoy.common.Constants;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PongSender {
    private final RabbitTemplate rabbitTemplate;

    public PongSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void pong(Ball ball) {
        String routingKey = Constants.PONG_ROUTING_KEY;
        String message = ball.toJSON();
        rabbitTemplate.convertAndSend(Application.topicExchangeName, routingKey, message);
    }
}
