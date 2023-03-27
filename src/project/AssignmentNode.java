package project;

public class AssignmentNode extends StatementNode {
    private String target;
    private Node expression;

    // target = expression
    public AssignmentNode(String statement, Node expression) {
        this.target = statement;
        this.expression = expression;
    }

    public String getTarget() {
        return target;
    }

    public Node getExpression() {
        return expression;
    }

    public String toString() {
        return (target != null ? target : "") + " = " + (expression != null ? expression : "") + "   ";
    }

}
