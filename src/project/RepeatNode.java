package project;

import java.util.ArrayList;

public class RepeatNode {
    private Node booleanExpression;
    private ArrayList<StatementNode> statements;

    public RepeatNode(Node booleanExpression, ArrayList<StatementNode> statements) {
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
        return "RepeatNode: " + booleanExpression + " statements: " + statements + " ";
    }
}
