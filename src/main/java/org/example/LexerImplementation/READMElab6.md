# Parser & Building an Abstract Syntax Tree

## Course
Formal Languages & Finite Automata

## Authors
Cretu Dumitru, with acknowledgments to Vasile Drumea and Irina Cojuhari

## Overview
Parsing, often synonymous with syntactic analysis, involves interpreting and understanding the syntactic structure of text. This process is crucial for compiling programs as it transforms the input text into a parse tree that includes semantic information usable in later compilation stages.

Similarly, an Abstract Syntax Tree (AST) represents the hierarchical structure of an input text, organizing data in abstraction layers that mirror the constructs of the original content. ASTs are pivotal in program analysis and various compilation processes.

## Objectives
1. **Understanding Parsing**: Learn the basics of parsing, its significance, and how it can be implemented.
2. **Exploring AST**: Become familiar with the concept and structure of Abstract Syntax Trees.
3. **Practical Implementation**:
    - Define a `TokenType` enum for use in lexical analysis.
    - Utilize regular expressions to categorize tokens.
    - Design and implement the necessary data structures for an AST.
    - Develop a simple parser program to extract syntactic information from text.

## Understanding Parsing: Theory and Programming

### What is Parsing?

Parsing, in computer science, refers to the process of analyzing a string of symbols, either in natural language or computer languages, based on the rules of a formal grammar. The goal of parsing is to determine if a given input string can be derived from the start symbol of the grammar. If the string can be derived, the parser then constructs a data structure—typically a parse tree or an Abstract Syntax Tree (AST)—that represents the syntactic structure of the string according to the rules of the grammar.

### Why is Parsing Important?

Parsing is crucial in various applications across computer science:
- **Compilers and Interpreters**: Parsing is the first phase of compilers and interpreters where source code is converted into an intermediate representation.
- **Data Interchange Formats**: Parsing allows machines to understand and manipulate structured data formats like XML, JSON, and HTML.
- **Natural Language Processing (NLP)**: It helps in understanding and deriving meaning from human languages.

### How Parsing Works

Parsing generally involves two main stages:
1. **Lexical Analysis (Tokenization)**: This first stage breaks down the input string into a list of tokens. Tokens are meaningful sequences of characters such as words, numbers, and symbols.
2. **Syntax Analysis**: This stage involves applying the grammar rules to the tokens to build a parse tree or AST.

### Grammar and Syntax Rules

Grammars for languages are defined using a notation such as Backus-Naur Form (BNF). A grammar consists of:
- **Terminals**: The basic symbols from which strings are formed.
- **Non-terminals**: Symbols that can be expanded into sequences of non-terminals and terminals.
- **Productions**: Rules that describe how terminals and non-terminals can be combined to form strings.

### Types of Parsers

There are several types of parsers, each suitable for different types of grammars and applications:
- **Top-Down Parsers**: Attempt to construct a parse tree from the top (starting symbol) and work down. Recursive descent parsers are a common type of top-down parser.
- **Bottom-Up Parsers**: Start with the input tokens and attempt to build up to the starting symbol. LR parsers (including variants like SLR, LALR, and CLR) are well-known bottom-up parsers.

## Understanding Abstract Syntax Trees (AST)

### What is an Abstract Syntax Tree (AST)?

An Abstract Syntax Tree (AST) is a tree representation of the abstract syntactic structure of source code written in a programming language. Each node of the tree denotes a construct occurring in the source code. The nodes of an AST are abstract in that they represent the high-level structure of the program. Unlike the parse trees which provide a low-level representation, incorporating every grammatical element from the source code, ASTs simplify the structure by abstracting away certain details.

### Purpose of AST

ASTs are used in various stages of a compiler but can also be utilized in other programming tools such as linters, formatters, and transpilers. The primary purposes of ASTs include:

- **Analysis**: ASTs allow the properties of the program to be analyzed, making them crucial for static analysis tools.
- **Optimization**: Compilers use ASTs to perform optimizations at a high level.
- **Code Generation**: The AST is transformed into a lower-level form, or directly into machine code.
- **Transformations**: ASTs make it possible to apply transformations to the source code, enabling features like macros, code obfuscation, and refactoring tools.

### Structure of an AST

The structure of an AST reflects the hierarchical syntax of the programming language. Here are the common components:

- **Nodes**: Represent constructs such as statements, expressions, declarations, and control flow elements.
- **Edges**: Define parent-child relationships, representing the syntactic structure of the source code.

## Implementation details



The process involves defining token types, parsing expressions and statements, and building the necessary AST nodes.

## 1. Token Types Definition

The first step in lexical analysis is defining the types of tokens that the lexer will recognize. This is done using an `enum` called `TokenType`:

```java
package org.example.LexerImplementation;

// Token types
public enum TokenType {
    NUMBER,
    IDENTIFIER,
    OPERATOR,
    LPAREN, // (
    RPAREN, // )
    LBRACE, // {
    RBRACE, // }
    IF,
    ELSE,
    EQUALS, // ==
    ASSIGN, // =
    SEMICOLON, // ;
    MULTIPLY, // *
    DIVIDE // /
}
```

This enumeration includes various token types such as numbers, identifiers, operators, and symbols that are pertinent to control structures like if statements and blocks.

## Lexer Implementation
The lexer (lexical analyzer) is responsible for converting the input text into a series of tokens. This part of the implementation is assumed to be handled by the Lexer class, which uses regular expressions to categorize the input text according to the TokenType definitions.

##  Abstract Syntax Tree (AST) Nodes
To represent different constructs of the language, we define several types of AST nodes. Each node type corresponds to a particular language construct such as arithmetic operations, variable references, and control structures.
```java
public class NumberNode extends ASTNode {
   private double value;

   public NumberNode(double value) {
      this.value = value;
   }

   // Visitor method
   public void accept(ASTVisitor visitor) {
      visitor.visit(this);
   }
}
public class BinaryOperationNode extends ASTNode {
   private ASTNode left;
   private ASTNode right;
   private String operator;

   public BinaryOperationNode(ASTNode left, ASTNode right, String operator) {
      this.left = left;
      this.right = right;
      this.operator = operator;
   }
   // Visitor method
   public void accept(ASTVisitor visitor) {
      visitor.visit(this);
   }
}
```

## Parser Implementation
The parser is responsible for constructing the Abstract Syntax Tree (AST) from the tokens generated by the lexer. The parser analyzes the tokens based on the grammar rules of the language and builds the corresponding AST nodes.
```java
public class Parser {
   private final List<Token> tokens;
   private int pos = 0;

   public Parser(Lexer lexer) {
      this.tokens = lexer.tokenize();
   }
```

## Visitor Pattern for AST Nodes

The Visitor pattern is a design pattern that allows adding new operations to classes without modifying them. In the context of AST nodes, the Visitor pattern enables the implementation of operations that traverse the AST and perform actions based on the node type.

```java
public interface ASTVisitor {
    void visit(NumberNode node);
    void visit(BinaryOperationNode node);
    void visit(IfStatementNode node);
}
```
### Output
```
Enter an expression to tokenize:
if ( p == 3 ) { q = 4.5 * p + d; } else { q = p - 1; }
IF: 'if'
LPAREN: '('
IDENTIFIER: 'p'
EQUALS: '=='
NUMBER: '3'
RPAREN: ')'
LBRACE: '{'
IDENTIFIER: 'q'
ASSIGN: '='
NUMBER: '4.5'
MULTIPLY: '*'
IDENTIFIER: 'p'
IDENTIFIER: 'd'
SEMICOLON: ';'
RBRACE: '}'
ELSE: 'else'
LBRACE: '{'
IDENTIFIER: 'q'
ASSIGN: '='
IDENTIFIER: 'p'
NUMBER: '1'
SEMICOLON: ';'
RBRACE: '}'
```

```
AST Output:
If
   Condition:
      Identifier: p
      Operator: ==
         Number: 3.0
   Then:
      Block
         Identifier: q
         Assign: =
            Number: 4.5
            Operator: *
               Identifier: p
         Identifier: d
   Else:
      Block
         Identifier: q
         Assign: =
            Identifier: p
         Number: 1.0
```

## Conclusion
The parser implementation detailed here sets the foundation for a flexible and extendable compiler or interpreter structure. By using ASTs and the visitor pattern, the system remains open to extensions like new node types or additional features such as code optimization and semantic analysis.
Understanding parsing and ASTs is essential for anyone interested in language design, compiler construction, or program analysis. By exploring these concepts and their practical implementation, developers can gain insights into the inner workings of programming languages and the tools used to process them.











































