package net.codetojoy.ping;

import net.codetojoy.common.Ball;

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

        if (! ball.isMaxedOut()) {
            String newMessage = "PING #: " + (ball.getNumHits() + 1);
            System.out.println("TRACER " + newMessage);

            Ball newBall = ball.hit(newMessage);
            pongSender.pong(newBall);
        } else {
            System.out.println("TRACER halting sequence. numHits: " + ball.getNumHits());
            System.out.println("TRACER ball: " + ball.toString());
        }
    }
}
