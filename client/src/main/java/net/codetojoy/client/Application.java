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
    private static String name;

    static String routingKey() { return routingKey; }
    static String name() { return name; }

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

    private static final String INPUT_PING = "i";
    private static final String INPUT_PONG = "o";
    private static final String INPUT_QUIT = "q";

    private static void processCommand() {
        Prompt prompt = new Prompt();
        String input = prompt.getInput("\n\ncmd: [I=ping, O=pong, Q=quit] ?", INPUT_PING, INPUT_PONG, INPUT_QUIT);

        if (input.equalsIgnoreCase(INPUT_PING)) {
            name = prompt.getInput("enter a name: "); 
            routingKey = Constants.PING_ROUTING_KEY;
        } else if (input.equalsIgnoreCase(INPUT_PONG)) {
            name = prompt.getInput("enter a name: "); 
            routingKey = Constants.PONG_ROUTING_KEY;
        } 
    }

    public static void main(String[] args) throws InterruptedException {
        processCommand();
        SpringApplication.run(Application.class, args).close();
    }
}



