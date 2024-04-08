package org.example.RegularExpressions;

public class WordAnalysis {

    // Method to analyze and describe a word according to predefined grammars
    public static void analyzeAndDescribe(String word) {
        // Initialize a description StringBuilder with analysis preamble
        StringBuilder description = new StringBuilder("Analysis and description of the word: ").append(word).append("\n");

        // Grammar 1: M?N^2(O|P)^3Q*R+
        if (word.matches("M?N{2}(O|P){3}Q*R+")) {
            description.append("The word matches grammar 1: M?N^2(O|P)^3Q*R+\n");
            // Check for the optional 'M' at the beginning
            if (word.startsWith("M")) {
                description.append("Processing 'M': the character 'M' is present at the beginning.\n");
            } else {
                description.append("Processing 'M': the character 'M' is absent.\n");
            }

            // Process 'N^2'
            description.append("Processing 'N^2': two consecutive 'N' characters are present.\n");

            // Process '(O|P)^3'
            int countO = word.length() - word.replace("O", "").length();
            int countP = word.length() - word.replace("P", "").length();
            if (countO + countP >= 3) {
                description.append("Processing '(O|P)^3': a combination of three 'O' or 'P' characters is present.\n");
            }

            // Process 'Q*'
            int countQ = word.length() - word.replace("Q", "").length();
            if (countQ > 0) {
                description.append("Processing 'Q*': 'Q' appears ").append(countQ).append(" time(s).\n");
            } else {
                description.append("Processing 'Q*': the character 'Q' is absent.\n");
            }

            // Process 'R+'
            int countR = word.length() - word.replace("R", "").length();
            if (countR > 0) {
                description.append("Processing 'R+': 'R' appears ").append(countR).append(" time(s).\n");
            }
        }

        // Grammar 2: (X|Y|Z){3}8+(9|0){2}
        else if (word.matches("(X|Y|Z){3}8+(9|0){2}")) {
            description.append("The word matches grammar 2: (X|Y|Z)^3 8^+ (9|0)^2\n");
            // Process '(X|Y|Z)^3'
            description.append("Processing '(X|Y|Z)^3': the first three characters are ").append(word.substring(0, 3)).append(".\n");
            // Process '8^+'
            int eightCount = word.substring(3, word.length() - 2).length();
            description.append("Processing '8^+': a sequence of ").append(eightCount).append(" '8' characters.\n");
            // Process '(9|0)^2'
            description.append("Processing '(9|0)^2': the last two characters are ").append(word.substring(word.length() - 2)).append(".\n");
        }
        // Grammar 3: (H|I)(J|K)L*N?
        else if (word.matches("(H|I)(J|K)L*N?")) {
            description.append("The word matches grammar 3: (H|I)(J|K)L*N?\n");
            // Process '(H|I)'
            description.append("Processing '(H|I)': the first character is ").append(word.charAt(0)).append(".\n");
            // Process '(J|K)'
            description.append("Processing '(J|K)': the second character is ").append(word.charAt(1)).append(".\n");
            // Determine the text after J|K and count 'L'
            String afterJK = word.substring(2);
            int lCount = afterJK.replaceAll("[^L]", "").length();
            description.append("Processing 'L*': the word contains ").append(lCount).append(" 'L' character(s).\n");
            // Check for optional 'N' at the end
            if (afterJK.endsWith("N")) {
                description.append("Processing 'N?': the word ends with 'N'.\n");
            }
        } else {
            // Case where the word does not match any known grammars
            description.append("The word does not match any of the known grammars.\n");
        }

        // Print the final analysis and description
        System.out.println(description.toString());
    }

    public static void main(String[] args) {
        // Examples of analyzing different words according to the predefined grammars
        analyzeAndDescribe("NNPOPQQRRR");
        analyzeAndDescribe("YYY8888890");
        analyzeAndDescribe("PIHGBJ");
    }
}
