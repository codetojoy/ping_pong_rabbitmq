package net.codetojoy.client;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class PromptTestCase {
    private final String[] options = {"a", "b", "c", "d"};
    private final Prompt prompt = new Prompt();

    @Test
    public void testFind_green() {
        String target = "b";

        // test
        Optional<String> result = prompt.find(target, options);

        assertEquals(target, result.get()); 
    }

    @Test
    public void testFind_missing() {
        String target = "z";

        // test
        Optional<String> result = prompt.find(target, options);

        assertFalse(result.isPresent());
    }
}
