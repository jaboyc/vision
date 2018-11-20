package com.jlogical.vision.compiler.values;

import com.jlogical.vision.compiler.script.Variable;
import com.jlogical.vision.compiler.script.elements.Command;
import com.jlogical.vision.project.CodeRange;

/**
 * Value that holds the name of a Variable and returns the value of it.
 */
public class VariableValue implements Value{

    /**
     * The name of the Variable.
     */
    private String variableName;

    /**
     * The range this Value is in.
     */
    private CodeRange range;

    /**
     * The Command that is holding this Value.
     */
    private Command commandHolder;

    /**
     * Creates a VariableValue with the given variableName, range, and commandHolder.
     */
    public VariableValue(String variableName, CodeRange range, Command commandHolder){
        this.variableName = variableName != null ? variableName : "";
        this.range = range;
        if(commandHolder == null){
            throw new IllegalArgumentException("commandHolder cannot be null!");
        }
        this.commandHolder = commandHolder;
    }

    @Override
    public Object getValue() {
        Variable variable = Variable.findVariable(variableName, commandHolder.getHatHolder(), commandHolder.getHatHolder().getScript());
        if(variable == null){
            throw new IllegalArgumentException("Variable '"+variableName+"' could not be found!");
        }
        return variable.getValue();
    }

    @Override
    public CodeRange getRange() {
        return range;
    }
}