package project;

import java.lang.reflect.Type;

public class BooleanCompareNode {

    private Node left;
    private Node right;
    private Type condition;

    public BooleanCompareNode(Node left, Node right, Token.TokenType operatorType) {
    }

    public Node getLeft(){
        return left;
    }
    public void setLeft(Node left){
        this.left = left;
    }
    public Node getRight(){
        return right;
    }
    public Type getOperator() {
        return condition;
    }

    @Override
    public String toString() {
        return "BooleanCompareNode{" +
                "left=" + left.toString() +
                ", right=" + right.toString() +
                ", condition=" + condition.toString() +
                '}';
    }
}
