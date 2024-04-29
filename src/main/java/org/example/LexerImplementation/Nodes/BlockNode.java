package org.example.LexerImplementation.Nodes;


import java.util.ArrayList;
import java.util.List;
import org.example.LexerImplementation.ASTNode;
import org.example.LexerImplementation.ASTVisitor;

/** Represents a block in the AST. */
public class BlockNode implements ASTNode {
    private final List<ASTNode> statements;

    public BlockNode() {
        this.statements = new ArrayList<>();
    }

    public void addStatement(ASTNode statement) {
        statements.add(statement);
    }

    public List<ASTNode> getStatements() {
        return statements;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{\n");
        for (ASTNode statement : statements) {
            builder.append(statement).append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}