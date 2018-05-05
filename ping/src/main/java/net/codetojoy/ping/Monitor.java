package net.codetojoy.ping;

import net.codetojoy.common.util.Sleeper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Monitor implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        new Sleeper().sleep("ping");
    }
}
