package project;

public class MathOpNode extends Node {

    private void setMathOp (Token.TokenType mathParam) {
        switch (mathParam) {
            case PLUS :
                mathOp = mathOperator.PLUS;
                break;
            case MINUS :
                mathOp = mathOperator.MINUS;
                break;
            case MULTIPLY:
                mathOp = mathOperator.MULTIPLY;
                break;
            case DIVIDES :
                mathOp = mathOperator.DIVIDE;
                break;
                case MODULUS :
                    mathOp = mathOperator.MODULUS;
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
