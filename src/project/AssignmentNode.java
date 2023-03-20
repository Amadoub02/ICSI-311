package project;

public class AssignmentNode {
    private VariableReferenceNode target;
    private Node expression;

    // target = expression
    public AssignmentNode(VariableReferenceNode statement, Node expression) {
        this.target = statement;
        this.expression = expression;
    }

    public VariableReferenceNode getTarget() {
        return target;
    }

    public Node getExpression() {
        return expression;
    }

    public String toString() {
        return (target != null ? target : "") + " = " + (expression != null ? expression : "") + "   ";
    }

}
