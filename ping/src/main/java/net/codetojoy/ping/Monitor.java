package net.codetojoy.ping;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Monitor implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("TRACER monitor: " + new Date());
            try { Thread.sleep(4000); } catch (Exception ex) {}
        }
    }
}
