package org.example.LexerImplementation;

import java.util.ArrayList;
import java.util.List;

// Lexer class
public class Lexer {
    private final String input;
    private int position = 0;
    private char currentChar;

    public Lexer(String input) {
        this.input = input + "\0"; // Sentinel character for EOF
        currentChar = this.input.charAt(position);
    }

    private void advance() {
        position++;
        if (position > input.length() - 1) {
            currentChar = '\0'; // EOF
        } else {
            currentChar = input.charAt(position);
        }
    }

    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    public Token getNextToken() {
        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue; // Continue scanning for the next token
            }

            if (Character.isDigit(currentChar)) {
                StringBuilder value = new StringBuilder();
                while (Character.isDigit(currentChar)) {
                    value.append(currentChar);
                    advance();
                }
                return new Token(TokenType.INTEGER, value.toString());
            }

            if (currentChar == '+') {
                advance();
                return new Token(TokenType.PLUS, "+");
            }

            if (currentChar == '-') {
                advance();
                return new Token(TokenType.MINUS, "-");
            }

            if (currentChar == '*') {
                advance();
                return new Token(TokenType.MULTIPLY, "*");
            }

            if (currentChar == '/') {
                advance();
                return new Token(TokenType.DIVIDE, "/");
            }

            if (currentChar == '=') {
                advance();
                return new Token(TokenType.EQUALS, "=");
            }
        }

        return new Token(TokenType.EOF, null);
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();
        Token token = getNextToken();
        while (token.type != TokenType.EOF) {
            tokens.add(token);
            token = getNextToken();
        }
        tokens.add(token); // Add EOF token
        return tokens;
    }
}
