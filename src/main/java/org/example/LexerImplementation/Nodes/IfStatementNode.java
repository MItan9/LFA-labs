package org.example.LexerImplementation.Nodes;

import org.example.LexerImplementation.ASTNode;
import org.example.LexerImplementation.ASTVisitor;

/** Represents an if statement in the AST. */
public class IfStatementNode implements ASTNode {
    private final ASTNode condition;
    private final ASTNode thenBranch;
    private final ASTNode elseBranch;

    public IfStatementNode(ASTNode condition, ASTNode thenBranch, ASTNode elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public ASTNode getCondition() {
        return condition;
    }

    public ASTNode getThenBranch() {
        return thenBranch;
    }

    public ASTNode getElseBranch() {
        return elseBranch;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "if ("
                + condition
                + ") "
                + thenBranch
                + (elseBranch != null ? " else " + elseBranch : "");
    }
}