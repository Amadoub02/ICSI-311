package project;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Interpreter {

    static HashMap<String, CallabaleNode> hashMapfunctions;
    static HashMap<String, InterpreterDataType> variablesMap;

    public Interpreter() {
        Interpreter.hashMapfunctions = hashMapfunctions;
    }

    //takes in a list of parameters and a function call node and match params to names
    //call interpretBlock on the function
    public static void interpretFunction(FunctionCallNode function, ArrayList<InterpreterDataType> params) {
        for (int i = 0; i < function.getParameters().size(); i++) {
            variablesMap.put(function.getParameters().get(i).getVariableName(), params.get(i));
        }
        if ((hashMapfunctions.get(function.getFunctionName()) instanceof FunctionNode node)) {
            for (VariableNode var : ((FunctionNode) hashMapfunctions.get(function.getFunctionName())).getArguments()) {
                if (var.getValue() instanceof IntegerNode) {
                    variablesMap.put(var.getName(), new IntegerDataType(var.getValue().toString()));
                } else if (var.getValue() instanceof RealNode) {
                    variablesMap.put(var.getName(), new RealDataType(var.getValue().toString()));
                } else if (var.getValue() instanceof StringNode) {
                    variablesMap.put(var.getName(), new StringDataType(var.getValue().toString()));
                } else if (var.getValue() instanceof CharacterNode) {
                    variablesMap.put(var.getName(), new CharacterDataType(var.getValue().toString().toCharArray()[0]));
                } else if (var.getValue() instanceof BooleanNode) {
                    variablesMap.put(var.getName(), new BooleanDataType(((BooleanNode) var.getValue()).getValue()));
                } else {
                    System.out.println("Error: Unknown data type");
                }
            }

        }
        FunctionNode functionNode = (FunctionNode) hashMapfunctions.get(function.getFunctionName());
        Interpreter.interpretBlock(functionNode.getBody(), variablesMap);

    }

    private static InterpreterDataType interpretBlock(ArrayList<StatementNode> statements, HashMap<String, InterpreterDataType> variablesMap) {
        for (StatementNode statement : statements) {
            if (statement instanceof FunctionCallNode functionCall) {
                String name = functionCall.getFunctionName();
                if (hashMapfunctions.containsKey(name)) {
                    FunctionCallNode current;

                    ArrayList<InterpreterDataType> params = new ArrayList<>();
                    ArrayList<String> names = new ArrayList<>();
                    functionCall.getParameters().stream().filter(param -> variablesMap.containsKey(param.getVariableName())).forEach(param -> {
                        Type paramType;
                        if (variablesMap.get(param.getVariableName()) instanceof IntegerDataType) {
                            params.add(variablesMap.get(param.getVariableName()));
                            names.add(param.getVariableName());
                        } else if (variablesMap.get(param.getVariableName()) instanceof RealDataType) {
                            params.add(variablesMap.get(param.getVariableName()));
                            names.add(param.getVariableName());
                        } else if (variablesMap.get(param.getVariableName()) instanceof StringDataType) {
                            params.add(variablesMap.get(param.getVariableName()));
                            names.add(param.getVariableName());
                        } else if (variablesMap.get(param.getVariableName()) instanceof CharacterDataType) {
                            params.add(variablesMap.get(param.getVariableName()));
                            names.add(param.getVariableName());
                        } else {
                            System.out.println("Error: Unknown data type");
                        }
                    });
                    if (statement instanceof IfNode) {
                        Node expression = ((IfNode) statement).getBooleanExpression();
                        ArrayList<StatementNode> block = ((IfNode) statement).getStatements();
                    } else if (statement instanceof WhileNode) {
                        BooleanCompareNode expression = ((WhileNode) statement).getBooleanExpression();
                        ArrayList<StatementNode> block = ((WhileNode) statement).getStatements();
                    } else if (statement instanceof RepeatNode) {
                        BooleanCompareNode expression = ((RepeatNode) statement).getBooleanExpression();
                        ArrayList<StatementNode> block = ((RepeatNode) statement).getStatements();
                    } else if (statement instanceof ForNode forNode) {
                        VariableReferenceNode variable = forNode.getVariableReference();
                        Node start = forNode.getStart();
                        ArrayList<StatementNode> block = forNode.getStatementNode();
                        Node end = forNode.getEnd();
                    }
                }
            }

        }
        return null;
    }

    //perform the math operation on the left and right nodes
    public Float Resolve(Node ASTNode) {
        if (ASTNode == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
        if (ASTNode instanceof RealNode) {
            return ((RealNode) ASTNode).getFloatValue();
        } else if (ASTNode instanceof IntegerNode) {
            return ((IntegerNode) ASTNode).getIntegerValue();
        } else {
            MathOpNode mathNode = (MathOpNode) ASTNode;
            MathOpNode.mathOperator mathOperator = MathOpNode.mathOperator.valueOf(mathNode.getOP());
            Node leftNode = mathNode.getLeft();
            Node rightNode = mathNode.getRight();
            float leftValue = Resolve(leftNode);
            float rightValue = Resolve(rightNode);
            float calculation;

            //switch statement to determine which math operator to use
            if (mathOperator == MathOpNode.mathOperator.PLUS) {
                calculation = leftValue + rightValue;
                return calculation;
            } else if (mathOperator == MathOpNode.mathOperator.MINUS) {
                calculation = leftValue - rightValue;
                return calculation;
            } else if (mathOperator == MathOpNode.mathOperator.MULTIPLY) {
                calculation = leftValue * rightValue;
                return calculation;
            } else if (mathOperator == MathOpNode.mathOperator.DIVIDE) {
                calculation = leftValue / rightValue;
                return calculation;
            } else {
                throw new RuntimeException("Invalid math operator");
            }
        }

    }

    private static BooleanNode getBooleanNode(BooleanCompareNode Node, Node left, Node right) {
        var op = Node.getOperator();
        if (left instanceof IntegerNode && right instanceof IntegerNode) {
            Float leftValue = ((IntegerNode) left).getIntegerValue();
            Float rightValue = ((IntegerNode) right).getIntegerValue();

            if (Objects.equals(op, ">")) {
                return new BooleanNode(leftValue > rightValue);
            }else if (Objects.equals(op, ">=")){
                return new BooleanNode(leftValue >= rightValue);
            }else if (Objects.equals(op, "<")) {
                return new BooleanNode(leftValue < rightValue);
            }else if (Objects.equals(op, "<=")) {
                return new BooleanNode(leftValue <= rightValue);
            }else if (Objects.equals(op, "=")) {
                return new BooleanNode(leftValue == rightValue);
            }else if (Objects.equals(op, "<>")) {
                return new BooleanNode(leftValue != rightValue);
            }
        }
        return null;
    }
}

