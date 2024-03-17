# Formal Languages & Finite Automata Coursework

## Introduction to Formal Languages, Regular Grammars, and Finite Automata

### Course: Formal Languages & Finite Automata
### Authors: Cretu Dumitru with acknowledgments to Vasile Drumea and Irina Cojuhari

---
# Lexer Implementation in Java

This report provides an overview and explanation of a Java package designed for lexical analysis, a fundamental concept in compiler design. Lexical analysis is the process of converting a sequence of characters into a sequence of tokens, which can be used for further processing such as parsing.

## Overview

The package `org.example.LexerImplementation` contains a set of classes that together implement a simple lexer. This lexer can tokenize arithmetic expressions containing integers, plus, minus, multiplication, division operators, equals signs, and whitespace.

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

The *Lexer* class is responsible for the lexical analysis of the input string. It converts the input into tokens.

Initialization
The constructor initializes the lexer with the input string and adds a sentinel character for EOF.

```java
Lexer(String input) {
    this.input = input + "\0"; // Sentinel character for EOF
    currentChar = this.input.charAt(position);
}
```