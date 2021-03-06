package com.jlogical.vision.compiler.values;

import com.jlogical.vision.compiler.exceptions.VisionException;
import com.jlogical.vision.compiler.script.elements.Command;
import com.jlogical.vision.project.CodeRange;
import com.jlogical.vision.project.Project;
import com.jlogical.vision.util.Calc;

import java.util.ArrayList;

/**
 * Value that holds either a math expression or logic expression.
 */
public class ExpressionValue implements Value {

    /**
     * Range the value is in.
     */
    private CodeRange range;

    /**
     * The text of this value.
     */
    private String text;

    /**
     * The Commmand that is holding this value.
     */
    private Command commandHolder;

    /**
     * List of Values in this ExpressionValue.
     */
    private ArrayList<Value> values;

    /**
     * Creates a new ExpressionValue with the given text, range, commandHolder, and project.
     */
    public ExpressionValue(String text, ArrayList<Value> values, CodeRange range, Command commandHolder){
        this.text = text;
        this.values = values;
        this.range = range;
        this.commandHolder = commandHolder;
    }

    @Override
    public Object getValue() throws VisionException {
        String newText = text;
        for(Value value : values){
            newText = newText.substring(0, value.getRange().getCharStart()) + value.getValue() + newText.substring(value.getRange().getCharEnd());
        }
        Object o = Calc.calc(this);
        if (o instanceof Double) {
            Double d = (Double) o;
            if (d == (int) d.doubleValue())
                return (int) d.doubleValue();
        }
        return o;
    }

    @Override
    public CodeRange getRange() {
        return range;
    }

    public Command getCommandHolder() {
        return commandHolder;
    }

    public String getText() {
        return text;
    }
}
