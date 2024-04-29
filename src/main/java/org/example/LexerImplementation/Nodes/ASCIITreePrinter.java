package org.example.LexerImplementation.Nodes;

import org.example.LexerImplementation.ASTNode;
import org.example.LexerImplementation.ASTVisitor;
import org.example.LexerImplementation.TokenType;

/** Prints the AST in a tree structure with ASCII art. */
public class ASCIITreePrinter implements ASTVisitor {
    private int depth = 0;

    /** Prints the AST in a tree structure with ASCII art. */
    private void printIndentation() {
        for (int i = 0; i < depth; i++) {
            System.out.print("   ");
        }
    }

    private void printNode(String label, String value) {
        printIndentation();
        System.out.println(label + ": " + value);
    }

    @Override
    public void visit(NumberNode node) {
        printNode(getTypeAsString(TokenType.NUMBER), Double.toString(node.getValue()));
    }

    @Override
    public void visit(VariableNode node) {
        printNode(getTypeAsString(TokenType.IDENTIFIER), node.getName());
    }

    @Override
    public void visit(AssignmentNode node) {
        node.getLeft().accept(this);
        printIndentation();
        System.out.println(getTypeAsString(TokenType.ASSIGN) + ": =");
        depth++;
        node.getRight().accept(this);
        depth--;
    }

    @Override
    public void visit(BinaryOperationNode node) {
        node.getLeft().accept(this);
        printIndentation();
        System.out.println(getTypeAsString(TokenType.OPERATOR) + ": " + node.getOperator());
        depth++;
        node.getRight().accept(this);
        depth--;
    }

    @Override
    public void visit(IfStatementNode node) {
        printIndentation();
        System.out.println(getTypeAsString(TokenType.IF));
        depth++;
        printIndentation();
        System.out.println("Condition:");
        depth++;
        node.getCondition().accept(this);
        depth--;
        printIndentation();
        System.out.println("Then:");
        depth++;
        node.getThenBranch().accept(this);
        depth--;
        if (node.getElseBranch() != null) {
            printIndentation();
            System.out.println(getTypeAsString(TokenType.ELSE) + ":");
            depth++;
            node.getElseBranch().accept(this);
            depth--;
        }
        depth--;
    }

    @Override
    public void visit(BlockNode node) {
        printIndentation();
        System.out.println("Block");
        depth++;
        for (ASTNode statement : node.getStatements()) {
            statement.accept(this);
        }
        depth--;
    }

    private String getTypeAsString(TokenType type) {
        return switch (type) {
            case NUMBER -> "Number";
            case IDENTIFIER -> "Identifier";
            case OPERATOR -> "Operator";
            case LPAREN -> "Left Parenthesis";
            case RPAREN -> "Right Parenthesis";
            case LBRACE -> "Left Brace";
            case RBRACE -> "Right Brace";
            case IF -> "If";
            case ELSE -> "Else";
            case EQUALS -> "Equals";
            case ASSIGN -> "Assign";
            case SEMICOLON -> "Semicolon";
            case MULTIPLY -> "Multiply";
            case DIVIDE -> "Divide";
            default -> "Unknown Type";
        };
    }
}
