package net.codetojoy.common.util;

import java.util.Date;

public class Sleeper {
    private static final int DELAY_IN_MILLIS = 4000;

    public void sleep(String logMessage) { 
        while (true) {
            System.out.println("TRACER " + new Date() + " " + logMessage);
            try { Thread.sleep(DELAY_IN_MILLIS); } catch (Exception ex) {}
        }
    }
}
