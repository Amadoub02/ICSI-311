package project;

import java.util.ArrayList;

public class IfNode extends StatementNode{

    private BooleanCompareNode booleanExpression;
    private ArrayList<StatementNode> statements;
    private IfNode ifNode;

    public IfNode(BooleanCompareNode booleanExpression, ArrayList<StatementNode> statements){
        this.booleanExpression = booleanExpression;
        this.statements = statements;
    }

    public IfNode(BooleanCompareNode booleanExpression, ArrayList<StatementNode> statements, IfNode ifNode){
        this.booleanExpression = booleanExpression;
        this.statements = statements;
        this.ifNode = ifNode;
    }

    public BooleanCompareNode getBooleanExpression() {
        return booleanExpression;
    }
    public ArrayList<StatementNode> getStatements() {
        return statements;
    }
    public IfNode getIfNode() {
        return ifNode;
    }
    public void setIfNode(IfNode ifNode) {
        this.ifNode = ifNode;
    }
    public String toString() {
        return "IfNode: " + booleanExpression + " statements: " + statements + " ifNode: " + ifNode + " ";
    }
    public void setElseStatements(ArrayList<StatementNode> statements) {
        this.statements = statements;
    }
    public void setStatements(ArrayList<StatementNode> statements) {
        this.statements = statements;
    }
}
