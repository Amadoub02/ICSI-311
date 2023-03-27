package project;

import java.util.ArrayList;

public class ForNode extends StatementNode{

    private VariableReferenceNode variableReference;
    private Node start;
    private Node end;
    private ArrayList<StatementNode> statementNode;

    public ForNode(VariableReferenceNode variableReference, Node start, Node end, ArrayList<StatementNode> statementNode) {
        this.variableReference = variableReference;
        this.start = start;
        this.end = end;
        this.statementNode = statementNode;
    }

    public Node getStart() {
        return start;
    }
    public Node getEnd() {
        return end;
    }
    public VariableReferenceNode getVariableReference() {
        return variableReference;
    }
    public ArrayList<StatementNode> getStatementNode() {
        return statementNode;
    }
    public String toString() {
        return "ForNode: + variableReference: " + variableReference + " start: " + start + " end: " + end + " statementNode: " + statementNode + " ";
    }

}
