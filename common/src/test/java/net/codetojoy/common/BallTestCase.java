package net.codetojoy.common;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class BallTestCase {
    @Test
    public void testHit_empty() {
        String json = "{\"payload\":[],\"id\":\"mozart\"}";
        Ball startBall = Ball.fromJSON(json);
        String message = "message 1";

        // test
        Optional<Ball> result = startBall.hit(message);

        assertTrue(result.isPresent());
        Ball ball = result.get();
        assertEquals(1, ball.getNumHits());
        List<String> payload = new ArrayList(ball.getPayload());
        assertEquals(1, payload.size());
    }

    @Test
    public void testHitWithJSON_green() {
        String json = "{\"payload\":[\"symphony #40\",\"piano concerto #22\"],\"id\":\"mozart\"}";
        Ball startBall = Ball.fromJSON(json);
        String message = "message 3";

        // test
        Optional<Ball> result = startBall.hit(message);

        assertTrue(result.isPresent());
        Ball ball = result.get();
        assertEquals(3, ball.getNumHits());
        List<String> payload = new ArrayList(ball.getPayload());
        assertEquals(3, payload.size());
    }

    @Test
    public void testHitWithJSON_isMaxedOut() {
        String json = "{\"payload\":[\"m1\",\"m2\",\"m3\",\"m4\",\"m5\",\"m6\",\"m7\",\"m8\",\"m9\",\"m10\"],\"id\":\"mozart\"}";
        Ball startBall = Ball.fromJSON(json);
        String message = "message 11";

        // test
        Optional<Ball> result = startBall.hit(message);

        assertFalse(result.isPresent());
    }

    @Test
    public void testFromJSON() {
        String json = "{\"payload\":[\"symphony #40\",\"piano concerto #22\"],\"id\":\"mozart\"}";

        // test
        Ball ball = Ball.fromJSON(json);

        assertEquals("mozart", ball.getId());
        List<String> payload = new ArrayList(ball.getPayload());
        assertEquals(2, payload.size());
        Collections.sort(payload);
        assertEquals("piano concerto #22", payload.get(0));
        assertEquals("symphony #40", payload.get(1));
    }

    @Test
    public void testToJSON() {
        Ball ball = new Ball("mozart");
        ball = ball.simpleHit("symphony #40");
        ball = ball.simpleHit("piano concerto #22");

        // test
        String result = ball.toJSON();

        String expected = "{\"payload\":[\"symphony #40\",\"piano concerto #22\"],\"id\":\"mozart\"}";
        assertEquals(expected, result);
    }
}
