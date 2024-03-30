# Regular Expressions Generator

This Java program is part of the coursework for "Formal Languages & Finite Automata". It focuses on the practical application of regular expressions, demonstrating how to generate valid combinations of symbols based on given regular expressions.

## Course Information

- **Topic:** Regular Expressions
- **Course:** Formal Languages & Finite Automata
- **Authors:** Cretu Dumitru and kudos to Vasile Drumea with Irina Cojuhari

## Objectives

The primary objectives of this project are to:

1. Explore what regular expressions are and their practical uses.
2. Write Java code to generate valid combinations of symbols conforming to specific regular expressions.
3. Address scenarios where symbols can be repeated an undefined number of times by setting a limit (to prevent the generation of extremely long combinations).
4. Optionally, illustrate the sequence of processing for each regular expression, highlighting the methodology applied.

## Implementation

The project consists of a Java package `org.example.RegularExpressions` containing a class `RegExpCombinations`. This class includes methods to generate all possible combinations for three complex patterns defined by given regular expressions.

### Key Methods and Their Explanations

### RegExpCombinations

A class that generates all possible combinations for three complex patterns defined by given regular expressions. It showcases Java's capabilities in handling sets, lists, and complex iteration patterns to explore all possible combinations that match the specified regular expressions.

#### Generating Pattern 1 Combinations

The `generatePattern1` method generates combinations based on the first pattern's rules. It starts with an optional 'M' or a space character, followed by combinations of 'O' and 'P', and concludes with a series of 'Q's and at least one 'R', limiting 'Q's to a maximum of five repetitions.

```java
private static List<String> generatePattern1() {
    Set<String> results = new HashSet<>();
    char[] optionalM = {' ', 'M'};
    char[] opOptions = {'O', 'P'};
    char[] qrOptions = {'Q', 'R'};

    for (char m : optionalM) {
        for (int i = 0; i < 8; i++) {
            String opCombination = "";
            for (int j = 0; j < 3; j++) {
                opCombination += opOptions[(i >> j) & 1];
            }
            for (int q = 0; q <= 5; q++) {
                String qs = "Q".repeat(q);
                for (int r = 1; r <= 5; r++) {
                    String rs = "R".repeat(r);
                    results.add(m + "NN" + opCombination + qs + rs);
                }
            }
        }
    }
    return List.copyOf(results);
}
```
This method utilizes bitwise operations to iterate through all combinations of 'O' and 'P', and a nested loop structure to manage the repetitions of 'Q' and 'R'.

### Generating Pattern 2 Combinations
The `generatePattern2` method creates combinations starting with sequences of 'X', 'Y', or 'Z', followed by a string of '8's and ending with combinations of '9' and '0'. This showcases the use of mathematical operations to navigate through permutations of the initial characters and subsequent parts of the pattern.
    
```java
private static List<String> generatePattern2() {
Set<String> results = new HashSet<>();
char[] xyzOptions = {'X', 'Y', 'Z'};
char[] nineZeroOptions = {'9', '0'};

    // Code omitted for brevity. Follows a similar structure to generatePattern1, adjusting for pattern 2's rules.
}
```

### Generating Pattern 3 Combinations
The `generatePattern3` method focuses on combinations of 'H' or 'I', followed by 'J' or 'K', with an optional sequence of 'L's up to five times, and optionally ending with 'N'. This illustrates handling optional elements and repetitions within a pattern.

```java
private static List<String> generatePattern3() {
    Set<String> results = new HashSet<>();
    char[] hiOptions = {'H', 'I'};
    char[] jkOptions = {'J', 'K'};

    // Code omitted for brevity. Illustrates handling optional elements and repetitions within the specified pattern.
}
```

### WordAnalysis

A new class designed to analyze strings and describe their structure based on predefined grammatical rules. It uses Java's pattern matching capabilities to determine if a word conforms to one of several specified grammars and then provides a detailed breakdown of its components.

#### Functionality

- **analyzeAndDescribe(String word)**: Analyzes a given word and prints a detailed description of its components according to the matching grammar. It supports the following grammars:
    - Grammar 1: `M?N^2(O|P)^3Q*R+`
    - Grammar 2: `(X|Y|Z){3}8+(9|0){2}`
    - Grammar 3: `(H|I)(J|K)L*N?`

```java
public static void main(String[] args) {
    WordAnalysis.analyzeAndDescribe("NNPPOQQRRRR"); // Analyzes according to Grammar 1
    WordAnalysis.analyzeAndDescribe("ZXY888809");   // Analyzes according to Grammar 2
    WordAnalysis.analyzeAndDescribe("HK");          // Analyzes according to Grammar 3
}
```

### Implementation Insights
The `WordAnalysis` class employs Java's `String.matches()` method to check if the input string conforms to a regular expression representing the grammar. It constructs a detailed description of the analysis process, highlighting how each part of the word matches the specific rules of the grammar. This approach not only demonstrates the application of regular expressions but also enhances understanding of grammatical analysis in computational linguistics.



## Challenges and Solutions
The development of this program involved various challenges, particularly in generating combinations that conform to specific rules and managing the complexity of nested loops for pattern generation. Solutions included the use of HashSet to avoid duplicate entries and carefully structuring loops to correctly implement the logic for each pattern.
Developing the WordAnalysis class involved understanding the intricacies of Java's regex engine and effectively mapping grammatical rules to regular expressions. One challenge was ensuring accurate and comprehensive descriptions for words that match multiple grammars. The solution included meticulous planning of regex patterns and extensive testing with varied input strings.

## Conclusion
The Regular Expressions Generator project showcases the practical application of regular expressions in generating valid symbol combinations based on specified patterns. By exploring complex regular expressions and implementing Java code to handle pattern generation, the project provides valuable insights into computational linguistics and formal language theory. The WordAnalysis class further demonstrates the versatility of regular expressions in analyzing word structures and mapping them to predefined grammatical rules. Overall, the project enhances understanding of regular expressions and their role in computational tasks, offering a hands-on approach to learning formal language concepts.


