package ass.elements;

import ass.AssElement;

public class AssInteger extends AssElement {

    private int value;

    public static final String NAME = "Integer";

    public AssInteger() {
        super(NAME);
    }

    public AssInteger(String name) {
        super(name);
    }

    public AssInteger(int value) {
        super(NAME);
        this.value = value;
    }

    @Override
    public void parse(String value) {
        if (value == null) return;
        this.value = Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
