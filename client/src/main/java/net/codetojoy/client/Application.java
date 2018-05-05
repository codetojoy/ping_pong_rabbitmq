package net.codetojoy.client;

import net.codetojoy.common.Constants;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    static final String topicExchangeName = Constants.EXCHANGE_NAME;
    static final String queueName = Constants.QUEUE_NAME;

    private static String routingKey;

    static String routingKey() {
        return routingKey;
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        System.out.println("TRACER Application ROUTING KEY IS " + routingKey);
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    public static void processArgs(String... args) {
        boolean isOk = false;

        if (args != null && args.length >= 1) {
            String arg = args[0];
            boolean isPing = Constants.PING.equalsIgnoreCase(arg);
            boolean isPong = Constants.PONG.equalsIgnoreCase(arg);
            isOk = (isPing || isPong);
            routingKey = isPing ? Constants.PING_ROUTING_KEY : Constants.PONG_ROUTING_KEY;
        }

        if (! isOk) {
            throw new IllegalArgumentException("check usage!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        processArgs(args);
        SpringApplication.run(Application.class, args).close();
    }
}
