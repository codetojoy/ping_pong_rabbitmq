package net.codetojoy.common;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class BallTestCase {
    @Test
    public void testCanary() {
        assertEquals(4, 2+2);
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
        ball = ball.hit("symphony #40");
        ball = ball.hit("piano concerto #22");

        // test
        String result = ball.toJSON();

        String expected = "{\"payload\":[\"symphony #40\",\"piano concerto #22\"],\"id\":\"mozart\"}";
        assertEquals(expected, result);
    }
}
