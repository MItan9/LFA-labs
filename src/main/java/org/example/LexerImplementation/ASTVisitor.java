package org.example.LexerImplementation;


import org.example.LexerImplementation.Nodes.AssignmentNode;
import org.example.LexerImplementation.Nodes.BinaryOperationNode;
import org.example.LexerImplementation.Nodes.BlockNode;
import org.example.LexerImplementation.Nodes.IfStatementNode;
import org.example.LexerImplementation.Nodes.NumberNode;
import org.example.LexerImplementation.Nodes.VariableNode;

/** Visitor interface for the AST. */
public interface ASTVisitor {
    void visit(NumberNode node);

    void visit(BinaryOperationNode node);

    void visit(IfStatementNode node);

    void visit(AssignmentNode node);

    void visit(BlockNode node);

    void visit(VariableNode node);
}
