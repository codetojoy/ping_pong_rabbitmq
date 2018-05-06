package net.codetojoy.ping;

import net.codetojoy.common.Ball;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class PingReceiver {
    static final String PING = "ping";

    private PongSender pongSender;

    public PingReceiver(PongSender pongSender) {
        this.pongSender = pongSender;
    }

    public void ping(String json) {
        Ball ball = Ball.fromJSON(json);
        System.out.println("TRACER received: \n" + ball.toString());
        String newMessage = "PING #: " + (ball.getNumHits() + 1);
        Optional<Ball> newBall = ball.hit(newMessage);

        if (newBall.isPresent()) {
            pongSender.pong(newBall.get());
        } else {
            System.out.println("TRACER halting sequence. numHits: " + ball.getNumHits());
            System.out.println("TRACER ball: " + ball.toString());
        }
    }
}
