package project;

public class CharacterNode extends Node{
    private char charValue;

    public CharacterNode(char charValue) {
        this.charValue = charValue;
    }

    public char getValue() {
        return charValue;
    }
    public String toString() {
        return "CharacterNode: " + charValue;
    }
}
