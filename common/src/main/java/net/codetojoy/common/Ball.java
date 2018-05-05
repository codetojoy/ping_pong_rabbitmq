package net.codetojoy.common;

import java.util.ArrayList;
import java.util.List;

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

    public Ball hit(String msg) {
        Ball newBall = new Ball(this.id);
        newBall.payload.addAll(this.payload);
        newBall.payload.add(msg);
        return newBall;
    }

    public boolean isMaxedOut() {
        boolean result = (getNumHits() >= MAX_HITS);
        return result;
    }

    public int getNumHits() {
        return payload.size();
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("id: " + id + "\n");
        buffer.append("payload: \n");

        for (String s : payload) {
            buffer.append(s + "\n");
        }

        return buffer.toString();
    }

    // for testing
    protected String getId() {
        return id;
    }
    
    protected List<String> getPayload() {
        return payload;
    }
}
