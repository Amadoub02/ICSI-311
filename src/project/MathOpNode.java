package project;

public class MathOpNode extends Node {

    private void setMathOp (Token.TokenType mathParam) {
        switch (mathParam) {
            case PLUS:
                this.mathOp = mathOperator.PLUS;
                break;
            case MINUS:
                this.mathOp = mathOperator.MINUS;
                break;
            case MULTIPLY:
                this.mathOp = mathOperator.MULTIPLY;
                break;
            case DIVIDE:
                this.mathOp = mathOperator.DIVIDE;
                break;
            case MODULUS:
                this.mathOp = mathOperator.MODULUS;
                break;
        }
    }

    enum mathOperator {
        PLUS, MINUS, MULTIPLY, DIVIDE, MODULUS
    }

    //variables used for mathOp node
    private mathOperator mathOp;
    private Node left;
    private Node right;

    MathOpNode(Token.TokenType MathOp, Node left, Node right) {
        //System.out.println(MathOp);
        setMathOp(MathOp);
        this.left = left;
        this.right = right;
    }
    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }
    public String getOP() {
        return this.mathOp.toString();
    }



    @Override
    public String toString() {
        return "MathOpNode(" + mathOp + " LEFT->" + left.toString() + " RIGHT->" + right.toString() + ")";
    }

}
