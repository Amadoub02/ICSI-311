package project;

import java.util.ArrayList;

public class WhileNode extends StatementNode{
    private Node booleanExpression;
    private ArrayList<StatementNode> statements;

    public WhileNode(Node booleanExpression, ArrayList<StatementNode> statements) {
        this.booleanExpression = booleanExpression;
        this.statements = statements;
    }
    public BooleanCompareNode getBooleanExpression() {
        return (BooleanCompareNode) booleanExpression;
    }
    public ArrayList<StatementNode> getStatements() {
        return statements;
    }
    public String toString() {
        return "WhileNode: " + booleanExpression + " statements: " + statements + " ";
    }
}
