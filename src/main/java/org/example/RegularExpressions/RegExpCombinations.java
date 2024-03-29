package org.example.RegularExpressions;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RegExpCombinations {

    // Utility method to append a set of characters to each string in a list, producing all possible combinations
    private static List<String> appendCharsToList(List<String> list, char[] chars) {
        Set<String> newSet = new HashSet<>();
        for (String base : list) {
            for (char c : chars) {
                newSet.add(base + c); // Append each character to the base string
            }
        }
        return List.copyOf(newSet); // Return as a List while ensuring no duplicates
    }

    // Generates and returns all combinations based on the specified pattern 1 rules
    private static List<String> generatePattern1() {
        Set<String> results = new HashSet<>();
        char[] optionalM = {' ', 'M'}; // Optional 'M' character at the beginning
        char[] opOptions = {'O', 'P'}; // Characters that can follow 'M' or space
        char[] qrOptions = {'Q', 'R'}; // Characters for the next part of the pattern

        // Iterate through all possible 'M' or space, followed by combinations of 'O' and 'P', and 'Q' and 'R'
        for (char m : optionalM) {
            for (int i = 0; i < 8; i++) { // Generating combinations for (O|P)^3
                String opCombination = "";
                for (int j = 0; j < 3; j++) {
                    opCombination += opOptions[(i >> j) & 1];
                }
                // Generating QR combinations with limited repetitions
                for (int q = 0; q <= 5; q++) { // Limiting Q repetitions to 5
                    String qs = "Q".repeat(q);
                    for (int r = 1; r <= 5; r++) { // Ensuring at least one 'R' up to a limit of 5
                        String rs = "R".repeat(r);
                        results.add(m + "NN" + opCombination + qs + rs); // Building the final string
                    }
                }
            }
        }

        return List.copyOf(results); // Return as a List while ensuring no duplicates
    }

    // Generates and returns all combinations based on the specified pattern 2 rules
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

        return List.copyOf(results); // Return as a List while ensuring no duplicates
    }

    // Generates and returns all combinations based on the specified pattern 3 rules
    private static List<String> generatePattern3() {
        Set<String> results = new HashSet<>();
        char[] hiOptions = {'H', 'I'}; // Characters for the first part of the pattern
        char[] jkOptions = {'J', 'K'}; // Characters for the second part of the pattern

        // Iterating through all combinations of HI and JK with optional L's and an optional N at the end
        for (char hi : hiOptions) {
            for (char jk : jkOptions) {
                for (int lCount = 0; lCount <= 5; lCount++) { // L* with a limit of 5
                    String ls = "L".repeat(lCount);
                    results.add(hi + "" + jk + ls); // Without 'N'
                    results.add(hi + "" + jk + ls + "N"); // With 'N'
                }
            }
        }

        return List.copyOf(results); // Return as a List while ensuring no duplicates
    }

    public static void main(String[] args) {
        // Generating combinations for all three patterns
        List<String> pattern1Combinations = generatePattern1();
        List<String> pattern2Combinations = generatePattern2();
        List<String> pattern3Combinations = generatePattern3();

        // Displaying the combinations for each pattern
        System.out.println("Pattern 1 Combinations Set:");
        System.out.println(pattern1Combinations);

        System.out.println("\nPattern 2 Combinations Set:");
        System.out.println(pattern2Combinations);

        System.out.println("\nPattern 3 Combinations Set:");
        System.out.println(pattern3Combinations);
    }
}
