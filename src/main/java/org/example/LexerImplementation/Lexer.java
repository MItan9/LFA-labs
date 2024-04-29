package org.example.LexerImplementation;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.example.LexerImplementation.TokenType;

/** Lexer for the language. */
public class Lexer {

    private final List<Token> tokens = new ArrayList<>();
    private static final Pattern tokenPatterns = Pattern.compile(
            "\\b(if|else)\\b|\\b\\d+\\.?\\d*|\\b[a-zA-Z][a-zA-Z0-9_]*|==|=|;|\\*|/|\\(|\\)|\\{|\\}"
    );
    private final Matcher matcher;

    public Lexer(String input) {
        this.matcher = tokenPatterns.matcher(input);
    }

    /** Tokenizes the input string. */
    public List<Token> tokenize() {
        while (matcher.find()) {
            if (matcher.group().equals("if")) {
                tokens.add(new Token(TokenType.IF, matcher.group()));
            } else if (matcher.group().equals("else")) {
                tokens.add(new Token(TokenType.ELSE, matcher.group()));
            } else if (matcher.group().matches("\\d+\\.?\\d*")) {
                tokens.add(new Token(TokenType.NUMBER, matcher.group()));
            } else if (matcher.group().matches("[a-zA-Z][a-zA-Z0-9_]*")) {
                tokens.add(new Token(TokenType.IDENTIFIER, matcher.group()));
            } else if (matcher.group().equals("==")) {
                tokens.add(new Token(TokenType.EQUALS, matcher.group()));
            } else if (matcher.group().equals("=")) {
                tokens.add(new Token(TokenType.ASSIGN, matcher.group()));
            } else if (matcher.group().equals(";")) {
                tokens.add(new Token(TokenType.SEMICOLON, matcher.group()));
            } else if (matcher.group().equals("*")) {
                tokens.add(new Token(TokenType.MULTIPLY, matcher.group()));
            } else if (matcher.group().equals("/")) {
                tokens.add(new Token(TokenType.DIVIDE, matcher.group()));
            } else if (matcher.group().equals("(")) {
                tokens.add(new Token(TokenType.LPAREN, matcher.group()));
            } else if (matcher.group().equals(")")) {
                tokens.add(new Token(TokenType.RPAREN, matcher.group()));
            } else if (matcher.group().equals("{")) {
                tokens.add(new Token(TokenType.LBRACE, matcher.group()));
            } else if (matcher.group().equals("}")) {
                tokens.add(new Token(TokenType.RBRACE, matcher.group()));
            }
        }
        return tokens;
    }
}