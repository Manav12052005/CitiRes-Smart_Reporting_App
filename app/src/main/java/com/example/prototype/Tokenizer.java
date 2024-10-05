package com.example.prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Tokenizer {
    // Define a pattern to match all non-word characters (punctuation, special characters)
    private static final Pattern NON_WORD_PATTERN = Pattern.compile("[^\\p{L}\\p{N}]+");  // Matches any character that is not a letter or a number

    public static List<String> tokenize(String input) {
        // Convert the input to lowercase and split by spaces to support grammar keywords
        return Arrays.stream(input.toLowerCase().trim().split("\\s+"))
                .filter(token -> !token.isEmpty())  // Remove empty tokens
                .collect(Collectors.toList());
    }

    public static List<String> tokenizeWithGrammar(String input) {
        // Tokenize input into grammar elements using both space and common punctuation
        List<String> tokens = new ArrayList<>();

        // Extract words and grammar elements (e.g., 'description:', 'location:')
        String[] splitInput = input.toLowerCase().trim().split("\\s+");
        for (String word : splitInput) {
            if (word.contains(":")) {
                tokens.add(word); // Grammar key-value separator like "description:"
            } else {
                tokens.add(word);
            }
        }

        return tokens;
    }
}
