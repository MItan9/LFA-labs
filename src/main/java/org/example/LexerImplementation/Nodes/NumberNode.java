package org.example.LexerImplementation.Nodes;


import org.example.LexerImplementation.ASTNode;
import org.example.LexerImplementation.ASTVisitor;

/** Represents a number in the AST. */
public class NumberNode implements ASTNode {
    private final double value;

    public NumberNode(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}