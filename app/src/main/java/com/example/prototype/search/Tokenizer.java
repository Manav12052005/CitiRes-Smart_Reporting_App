package com.example.prototype.search;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/**
 * The Tokenizer class ensures that the input is standardized
 * by removing special characters and splitting
 * the input based on spaces.
 * @author Harry Xia u7556816
 */
public class Tokenizer {
    // Matches any character that is not a letter, number, or colon
    private static final Pattern NON_WORD_PATTERN = Pattern.compile("[^\\p{L}\\p{N}:]+");

    public static List<String> tokenize(String input) {

        // Replace non-word characters with spaces to make splitting more straightforward
        String cleanedInput = NON_WORD_PATTERN.matcher(input.toLowerCase().trim()).replaceAll(" ");

        // Split by spaces and filter out empty tokens
        List<String> tokens = Arrays.stream(cleanedInput.split("\\s+"))
                .filter(token -> !token.isEmpty())  // Remove empty tokens
                .collect(Collectors.toList());

        return tokens;
    }
}
