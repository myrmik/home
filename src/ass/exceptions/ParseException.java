package ass.exceptions;

import java.util.*;

public class ParseException extends Exception {
    private String name;
    private String type;
    private String value;
    private Exception exception;
    static private List<ParseException> stack = new ArrayList<ParseException>();

    public ParseException(String name, String type, String value, Exception exception) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.exception = exception;

        stack.add(this);
    }

    public String printStack() {
        StringBuffer sb = new StringBuffer();
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (i != stack.size() - 1) {
                sb.append("\n\t");
            }
            sb.append(stack.get(i));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Error: element parsing. Name=" + name + ", type=" + type + ", value=" + value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
