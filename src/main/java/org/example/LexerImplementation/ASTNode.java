package org.example.LexerImplementation;

public interface ASTNode {
    void accept(ASTVisitor visitor);
}