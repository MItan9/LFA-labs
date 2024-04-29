package org.example.LexerImplementation.Nodes;



import org.example.LexerImplementation.ASTNode;
import org.example.LexerImplementation.ASTVisitor;

/** Represents an assignment in the AST. */
public class AssignmentNode implements ASTNode {
    private final ASTNode left;
    private final ASTNode right;

    public AssignmentNode(ASTNode left, ASTNode right) {
        this.left = left;
        this.right = right;
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return left + " = " + right;
    }
}