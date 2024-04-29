package org.example.LexerImplementation.Nodes;


import org.example.LexerImplementation.ASTNode;
import org.example.LexerImplementation.ASTVisitor;

/** Represents a number in the AST. */
public class BinaryOperationNode implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;
    private final String operator;

    public BinaryOperationNode(ASTNode left, ASTNode right, String operator) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Operands cannot be null.");
        }
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return left + " " + operator + " " + right;
    }
}