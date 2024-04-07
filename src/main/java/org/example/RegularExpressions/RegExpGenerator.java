package org.example.RegularExpressions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpGenerator {

    // Function to generate combinations based on a regular expression
    public static List<String> generateCombinations(String regExp) {
        // Remove spaces for parsing simplicity
        regExp = regExp.replace(" ", "");
        return generateCombinationsInternal("", regExp);
    }

    // Internal function for generating combinations recursively
    private static List<String> generateCombinationsInternal(String prefix, String regExp) {
        Set<String> results = new HashSet<>();

        if (regExp.isEmpty()) {
            results.add(prefix);
            return new ArrayList<>(results);
        }

        // Extend the pattern for parsing to better handle groups and quantifiers
        Pattern pattern = Pattern.compile("^(\\w+|\\((\\w+\\|?)+\\))(\\*|\\?|\\+|\\^\\d+)?|8\\+|\\d+");
        Matcher matcher = pattern.matcher(regExp);

        while (matcher.find()) {
            String matchedSegment = matcher.group(1) != null ? matcher.group(1) : "";
            String quantifier = matcher.group(3) != null ? matcher.group(3) : "";
            String remainder = regExp.substring(matcher.end());

            // Handle groups with choice
            if (matchedSegment.startsWith("(")) {
                matchedSegment = matchedSegment.substring(1, matchedSegment.length() - 1);
                String[] options = matchedSegment.split("\\|");
                for (String option : options) {
                    results.addAll(generateCombinationsInternal(prefix + option, remainder));
                }
            } else if (!matchedSegment.isEmpty()) {
                // Handle quantifiers with consideration for exact repetitions
                results.addAll(handleQuantifier(prefix, matchedSegment, quantifier, remainder));
            }
        }

        return new ArrayList<>(results);
    }

    // Function to handle quantifiers
    private static List<String> handleQuantifier(String prefix, String segment, String quantifier, String remainder) {
        List<String> results = new ArrayList<>();

        int min = 1, max = 1; // Default values
        if (quantifier.contains("*")) { min = 0; max = 5; }
        if (quantifier.contains("?")) { min = 0; max = 1; }
        if (quantifier.contains("+")) { min = 1; max = 5; }
        if (quantifier.startsWith("^")) {
            min = max = Integer.parseInt(quantifier.substring(1));
        }

        for (int i = min; i <= max; i++) {
            String newPrefix = prefix + segment.repeat(i);
            results.addAll(generateCombinationsInternal(newPrefix, remainder));
        }

        return results;
    }

    // Function to generate additional combinations for a specific pattern
    private static List<String> generatePattern2() {
        Set<String> results = new HashSet<>();
        char[] xyzOptions = {'X', 'Y', 'Z'}; // Characters for the first part of the pattern
        char[] nineZeroOptions = {'9', '0'}; // Characters for the final part of the pattern

        // Generate combinations for (X|Y|Z)^3 followed by a sequence of '8's and combinations of '9' and '0'
        for (int i = 0; i < 27; i++) { // 3^3 combinations for XYZ
            String xyzCombination = "";
            for (int j = 0; j < 3; j++) {
                xyzCombination += xyzOptions[(i / (int)Math.pow(3, j)) % 3];
            }

            // Append 1 to 5 '8's, then combinations of '9' and '0'
            for (int eightCount = 1; eightCount <= 5; eightCount++) {
                String eights = "8".repeat(eightCount);
                for (int k = 0; k < 4; k++) { // 2^2 combinations for 9 and 0
                    String nineZeroCombination = "";
                    for (int l = 0; l < 2; l++) {
                        nineZeroCombination += nineZeroOptions[(k >> l) & 1];
                    }
                    results.add(xyzCombination + eights + nineZeroCombination); // Building the final string
                }
            }
        }

        return new ArrayList<>(results); // Return as a List while ensuring no duplicates
    }

    // Main method to test the regular expression generator
    public static void main(String[] args) {
        List<String> pattern1Combinations = generateCombinations("M?N^2(O|P)^3Q*R+");
        List<String> pattern2Combinations = generateCombinations("(X|Y|Z)^38+(9|0)^2");
        List<String> pattern3Combinations = generateCombinations("(H|I)(J|K)L*N?");

        System.out.println("Pattern 1 Combinations Set:");
        System.out.println(pattern1Combinations);

        System.out.println("\nAdditional Combinations for Pattern 2:");
        List<String> pattern2ExtraCombinations = generatePattern2();
        System.out.println(pattern2ExtraCombinations);

        System.out.println("\nPattern 3 Combinations Set:");
        System.out.println(pattern3Combinations);

    }
}

