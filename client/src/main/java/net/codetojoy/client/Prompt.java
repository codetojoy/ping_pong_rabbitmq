package net.codetojoy.client;

import java.util.*;

public class Prompt {
    private static final String QUIT = "q";

    public String getInput(String prompt, String ...options) {
        String input = "";
        boolean isMatch = false;

        System.out.println(prompt);

        while (!isMatch) {
            Scanner scanner = new java.util.Scanner(System.in);
            input = scanner.nextLine();
           
            if (options == null || options.length == 0) {
                isMatch = true;
            } else {
                isMatch = matchInput(input, options);
            } 
        }

        return input;
    }

    protected Optional<String> find(String target, String[] options) {
        Optional<String> result = Arrays.stream(options)
                                        .filter( x -> x.equalsIgnoreCase(target) )
                                        .findFirst();

        return result;
    }

    private boolean matchInput(String input, String[] options) {
        Optional<String> result = find(input, options);
        boolean isMatch = result.isPresent();

        if (isMatch) {
            if (QUIT.equalsIgnoreCase(result.get())) {
                System.out.println("quitting ...");
                System.exit(0);
            }
        } else {
            System.out.println("try again: ");
        }

        return isMatch;
    }
}
