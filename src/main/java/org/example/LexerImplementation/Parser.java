package org.example.LexerImplementation;



import java.util.List;
import org.example.LexerImplementation.Nodes.*;



/** Parses a list of tokens into an abstract syntax tree. */
public class Parser {
    private final List<Token> tokens;
    private int pos = 0;

    public Parser(Lexer lexer) {
        this.tokens = lexer.tokenize();
    }

    public ASTNode parse() {
        return parseStatement();
    }

    /** Parses a statement. */
    private ASTNode parseStatement() {
        if (match(TokenType.IF)) {
            return parseIfStatement();
        }
        return parseExpression();
    }

    /** Parses an if statement. */
    private IfStatementNode parseIfStatement() {
        expect(TokenType.LPAREN, "Expected '(' after 'if'");
        ASTNode condition = parseExpression();
        expect(TokenType.RPAREN, "Expected ')' after condition");

        ASTNode thenBranch = parseBlock();
        ASTNode elseBranch = null;
        if (match(TokenType.ELSE)) {
            elseBranch = parseBlock();
        }

        return new IfStatementNode(condition, thenBranch, elseBranch);
    }

    /** Parses a block of statements. */
    private BlockNode parseBlock() {
        expect(TokenType.LBRACE, "Expected '{'");
        BlockNode block = new BlockNode();
        while (!check(TokenType.RBRACE) && !isAtEnd()) {
            block.addStatement(parseStatement());
            if (match(TokenType.SEMICOLON)) {
                continue;
            }
        }
        expect(TokenType.RBRACE, "Expected '}'");
        return block;
    }

    /** Parses an expression. */
    private ASTNode parseExpression() {
        return parseAssignment();
    }

    /** Parses an assignment. */
    private ASTNode parseAssignment() {
        ASTNode left = parseEquality();
        if (match(TokenType.ASSIGN)) {
            ASTNode value = parseAssignment();
            return new AssignmentNode(left, value);
        }
        return left;
    }

    /** Parses an equality expression. */
    private ASTNode parseEquality() {
        ASTNode expr = parseTerm();
        while (match(TokenType.EQUALS)) {
            Token operator = previous();
            ASTNode right = parseTerm();
            expr = new BinaryOperationNode(expr, right, operator.getText());
        }
        return expr;
    }

    /** Parses a term. */
    private ASTNode parseTerm() {
        ASTNode expr = parseFactor();
        while (match(TokenType.MULTIPLY, TokenType.DIVIDE)) {
            Token operator = previous();
            ASTNode right = parseFactor();
            expr = new BinaryOperationNode(expr, right, operator.getText());
        }
        return expr;
    }

    /** Parses a factor. */
    private ASTNode parseFactor() {
        if (match(TokenType.NUMBER)) {
            return new NumberNode(Double.parseDouble(previous().getText()));
        } else if (match(TokenType.IDENTIFIER)) {
            return new VariableNode(previous().getText());
        } else if (match(TokenType.LPAREN)) {
            ASTNode expression = parseExpression();
            expect(TokenType.RPAREN, "Expected ')' after expression");
            return expression;
        }
        throw new RuntimeException("Unexpected token: " + peek().getText());
    }

    /** Returns the current token. */
    private Token peek() {
        if (isAtEnd()) throw new RuntimeException("Unexpected end of input");
        return tokens.get(pos);
    }

    /** Returns the previous token. */
    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    /** Advances the parser to the next token. */
    private Token advance() {
        if (!isAtEnd()) pos++;
        return previous();
    }

    /** Returns true if the parser is at the end of the input. */
    private boolean isAtEnd() {
        return pos >= tokens.size();
    }

    /** Returns the previous token. */
    private Token previous() {
        return tokens.get(pos - 1);
    }

    /** Checks if the current token is of the given type. */
    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return tokens.get(pos).getType() == type;
    }

    /** Advances the parser to the next token and checks if it is of the given type. */
    private void expect(TokenType type, String errorMessage) {
        if (!check(type)) throw new RuntimeException(errorMessage);
        advance();
    }
}