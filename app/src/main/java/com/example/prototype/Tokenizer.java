package com.example.prototype;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Tokenizer {
    // Define a pattern to match all non-word characters (punctuation, special characters)
    private static final Pattern NON_WORD_PATTERN = Pattern.compile("[^\\p{L}\\p{N}]+");  // Matches any character that is not a letter or a number

    public static List<String> tokenize(String input) {
        // Convert the input to lowercase, split by non-word characters, trim, and remove empty strings
        return Arrays.stream(NON_WORD_PATTERN.split(input.toLowerCase().trim()))
                .filter(token -> !token.isEmpty())  // Remove empty tokens
                .collect(Collectors.toList());
    }
}