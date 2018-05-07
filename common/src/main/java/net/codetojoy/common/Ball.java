package net.codetojoy.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;

public class Ball {
    private static final int MAX_HITS = 10;

    private final List<String> payload = new ArrayList<>();
    private final String id;

    public Ball(String id) {
        this.id = id;
    }

    public static Ball fromJSON(String json) {
        Gson gson = new Gson();
        Ball ball = gson.fromJson(json, Ball.class);
        return ball;
    }

    public String toJSON() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public Optional<Ball> hit(String msg) {
        Optional<Ball> result = Optional.empty();

        if (! isMaxedOut()) {
            Ball newBall = simpleHit(msg);
            result = Optional.of(newBall);
        }

        return result;
    }


    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("id: " + id + "\n");
        buffer.append("payload: \n");

        payload.stream().forEach( s -> buffer.append(s + "\n") ); 

        return buffer.toString();
    }

    public int getNumHits() {
        return payload.size();
    }

    // ------------ internal 

    protected Ball simpleHit(String msg) {
        Ball newBall = new Ball(this.id);
        newBall.payload.addAll(this.payload);
        newBall.payload.add(msg);
        return newBall;
    }

    protected boolean isMaxedOut() {
        boolean result = (getNumHits() >= MAX_HITS);
        return result;
    }

    // for testing

    protected String getId() {
        return id;
    }

    protected List<String> getPayload() {
        return payload;
    }
}
