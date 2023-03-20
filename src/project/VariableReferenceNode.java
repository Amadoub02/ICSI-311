package project;

public class VariableReferenceNode {
    private String name;

    public VariableReferenceNode(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
