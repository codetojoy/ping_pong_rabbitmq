package net.codetojoy.pong;

import net.codetojoy.common.Ball;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class PongReceiver {
    static final String PONG = "pong";

    private PingSender pingSender;

    public PongReceiver(PingSender pingSender) {
        this.pingSender = pingSender;
    }

    public void pong(String json) {
        Ball ball = Ball.fromJSON(json);
        System.out.println("TRACER received: \n" + ball.toString());
        String newMessage = "     #: " + (ball.getNumHits() + 1) + " PONG";
        Optional<Ball> newBall = ball.hit(newMessage);

        if (newBall.isPresent()) {
            pingSender.ping(newBall.get());
        } else {
            System.out.println("TRACER halting sequence. numHits: " + ball.getNumHits());
            System.out.println("TRACER ball: " + ball.toString());
        }
    }
}
