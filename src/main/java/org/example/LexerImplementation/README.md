# Topic: Lexer & Scanner

## Course: Formal Languages & Finite Automata
### Authors: Cretu Dumitru with acknowledgments to Vasile Drumea and Irina Cojuhari

---
# Lexer Implementation in Java

This report provides an overview and explanation of a Java package designed for lexical analysis, a fundamental concept in compiler design. Lexical analysis is the process of converting a sequence of characters into a sequence of tokens, which can be used for further processing such as parsing.

## Overview


The term lexer comes from lexical analysis which, in turn, represents the process of extracting lexical tokens from a string of characters. There are several alternative names for the mechanism called lexer, for example tokenizer or scanner. The lexical analysis is one of the first stages used in a compiler/interpreter when dealing with programming, markup or other types of languages.The tokens are identified based on some rules of the language and the products that the lexer gives are called lexemes. So basically the lexer is a stream of lexemes. Now in case it is not clear what's the difference between lexemes and tokens, there is a big one. The lexeme is just the byproduct of splitting based on delimiters, for example spaces, but the tokens give names or categories to each lexeme. So the tokens don't retain necessarily the actual value of the lexeme, but rather the type of it and maybe some metadata.
The implemented lexer can tokenize arithmetic expressions containing integers, plus, minus, multiplication, division operators, equals signs, and whitespace.

### Classes

#### `TokenType` Enum

The `TokenType` enum defines the different types of tokens that the lexer can recognize. These include:

- `INTEGER`: Represents numeric values.
- `PLUS`: Represents the '+' operator.
- `MINUS`: Represents the '-' operator.
- `MULTIPLY`: Represents the '*' operator.
- `DIVIDE`: Represents the '/' operator.
- `EQUALS`: Represents the '=' operator.
- `WHITESPACE`: Although used internally, whitespace is typically ignored and not returned as a token.
- `EOF`: Represents the end of the input.

```java
enum TokenType {
    INTEGER, PLUS, MINUS, MULTIPLY, DIVIDE, EQUALS, WHITESPACE, EOF
}
```
The *Token* class represents a token, consisting of a type and a value.
```java
class Token {
    TokenType type;
    String value;

    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Value: '%s'", type, value);
    }
}
```
### Lexer Class
The *Lexer* class is responsible for the lexical analysis of the input string. It converts the input into tokens.

#### Initialization

The constructor initializes the lexer with the input string and adds a sentinel character for EOF.

```java
Lexer(String input) {
    this.input = input + "\0"; // Sentinel character for EOF
    currentChar = this.input.charAt(position);
}
```

#### Methods
**advance():** Advances the position in the input string and updates the current character.

**skipWhitespace():** Skips whitespace characters.

**getNextToken():** Identifies the next token in the input string.

**tokenize():** Tokenizes the entire input string.

Example of **getNextToken** method:

```java
Token getNextToken() {
    while (currentChar != '\0') {
        if (Character.isWhitespace(currentChar)) {
            skipWhitespace();
            continue;
        }
        // Checks for digits, operators, etc. to return the corresponding token.
    }
    return new Token(TokenType.EOF, null);
}
```

### LexerDemo Class
The *LexerDemo* class demonstrates the usage of the lexer by tokenizing an input string and printing the resulting tokens.

```java
public class LexerDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an arithmetic expression:");
        String input = scanner.nextLine();

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }

        scanner.close();
    }
}
```
### Objectives Achieved
1. **Understanding Lexical Analysis:** This report and the accompanying code provide a clear introduction to lexical analysis, highlighting its role in interpreting and compiling programming languages.

2. **Familiarity with Lexer Inner Workings:** The detailed explanation and code examples demonstrate how a lexer scans input text, categorizes segments of text into tokens, and prepares these tokens for further parsing or compilation.

3. **Implementing and Demonstrating a Lexer:** Through the provided **LexerImplementation** package, we've implemented a simple lexer capable of tokenizing arithmetic expressions, showcasing the practical application of lexical analysis concepts.

4. **Understanding Token Types:** The `TokenType` enum and `Token` class illustrate the different types of tokens that a lexer can recognize and how these tokens are represented.

## Conclusion
The lexer implementation in Java offers a foundational understanding of lexical analysis, illustrating how to convert a string input into a series of tokens for further processing in language parsing and compilation. By exploring this example, users gain practical experience in writing a lexer and understanding its role in compiler design and interpretation processes.