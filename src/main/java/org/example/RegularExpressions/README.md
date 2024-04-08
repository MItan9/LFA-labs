# Regular Expressions Generator

This Java program is part of the coursework for "Formal Languages & Finite Automata". It focuses on the practical application of regular expressions, demonstrating how to generate valid combinations of symbols based on given regular expressions.

## Course Information

- **Topic:** Regular Expressions
- **Course:** Formal Languages & Finite Automata
- **Authors:** Cretu Dumitru and kudos to Vasile Drumea with Irina Cojuhari

*Regular expressions* (regex) are a powerful tool for matching patterns within text. They use a specialized syntax to define a search pattern, making it possible to perform complex text searches, replacements, and parsing tasks that would be cumbersome or impossible with standard string methods alone. Regular expressions are used in programming languages, text editors, command-line utilities, and more, due to their versatility and efficiency in handling text-based data.

The essence of regex lies in its language, composed of literals, meta-characters, character classes, quantifiers, anchors, and groups. Each element plays a specific role, from representing individual characters to defining broad sets of characters, and from specifying the occurrence of patterns to marking positions within the text. Such a rich syntax enables regex to perform intricate text searches, replacements, and parsing operations that would otherwise be cumbersome or outright impossible with traditional string methods.

Regular expressions are employed across a diverse spectrum of tasks. They excel in searching and matching operations, where they can quickly locate specific patterns within text, such as email addresses, phone numbers, or custom identifiers. In the realm of data validation, regex ensures that inputs or data adhere to predefined formats, a critical aspect of maintaining data integrity and security. Furthermore, regex facilitates advanced text manipulation, enabling users to conduct complex replacements or deletions based on pattern matches. It also serves as a powerful tool for parsing and extracting information from logs, documents, and other text sources, transforming unstructured or semi-structured data into structured formats. Additionally, regex is utilized in syntax highlighting within text editors and IDEs, distinguishing and styling different parts of code according to language syntax rules.

**Benefits:**

- *Efficiency:* Allows for the processing of large texts quickly.
- *Versatility:* Applicable to virtually any programming language and many tools.
- *Precision:* Can match very specific patterns that are difficult to define with standard string methods.

**Limitations:**

- *Complexity:* Regex patterns can become difficult to read and maintain, especially for complex patterns.
- *Performance:* Inefficient regex patterns can be slow and resource-intensive on large texts.
- *Learning Curve:* Requires time to learn and master the syntax and nuances of regular expressions.


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

## Code Overview
The program consists of the following components:
- **`RegExpGenerator` class**: Contains methods for generating combinations based on regular expressions.
  - `generateCombinations(String regExp)`: Takes a regular expression as input and returns a list of combinations.
  - `generateCombinationsInternal(String prefix, String regExp)`: Internal method for generating combinations recursively.
  - `handleQuantifier(String prefix, String segment, String quantifier, String remainder)`: Handles quantifiers (*, ?, +, ^n) in the regular expression.
- **`main` method**: Entry point of the program where regular expressions are processed and combinations are generated.

### `generateCombinationsInternal` Method
This method recursively generates combinations based on the provided regular expression.

```java
    private static List<String> generateCombinationsInternal(String prefix, String regExp) {
        Set<String> results = new HashSet<>();

        if (regExp.isEmpty()) {
        results.add(prefix);
        return new ArrayList<>(results);
        }

        // Extend the pattern for parsing to better handle groups and quantifiers
        Pattern pattern = Pattern.compile("^(\\w+|\\((\\w+\\|?)+\\))(\\*|\\?|\\+|\\^\\d+)?|8\\+|\\d+");
        Matcher matcher = pattern.matcher(regExp);

        ...

        return new ArrayList<>(results);
        }
```         

### `handleQuantifier` Method
This method handles quantifiers (*, ?, +, ^n) in the regular expression.

```java
    private static Set<String> handleQuantifier(String prefix, String segment, String quantifier, String remainder) {
        Set<String> results = new HashSet<>();
        if (quantifier.isEmpty()) {
        results.add(prefix + segment);
        } else if (quantifier.equals("*")) {
        results.add(prefix);
        results.add(prefix + segment);
        results.addAll(handleQuantifier(prefix + segment, segment, quantifier, remainder));
        } else if (quantifier.equals("?")) {
        results.add(prefix);
        results.add(prefix + segment);
        ...
        return results;
        }
```

### Features
The program supports the following features:
- **Universal Compatibility:** The program is designed to handle a wide range of regular expressions, making it suitable for various use cases.
- **Flexible:** It supports quantifiers, groups, and different character sets, providing flexibility in defining regular expressions.
- **Efficient:** The program generates combinations efficiently, even for complex regular expressions, thanks to its recursive approach.
- **Scalable:** Additional methods can be added to extend its functionality further.

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


