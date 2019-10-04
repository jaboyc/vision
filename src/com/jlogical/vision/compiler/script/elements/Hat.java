package com.jlogical.vision.compiler.script.elements;

import com.jlogical.vision.api.elements.CustomHat;
import com.jlogical.vision.compiler.exceptions.VisionException;
import com.jlogical.vision.compiler.script.Script;
import com.jlogical.vision.compiler.script.Variable;
import com.jlogical.vision.compiler.values.Value;
import com.jlogical.vision.project.CodeRange;

import java.util.ArrayList;

/**
 * Stores a list of commands that is run when an event occurs.
 */
public class Hat extends CompiledElement<CustomHat> {

    /**
     * List of Commands in this Hat.
     */
    private ArrayList<Command> commands;

    /**
     * The Script the Hat is in.
     */
    private Script script;

    /**
     * List of local Variables in the Hat.
     */
    private ArrayList<Variable> variables;

    /**
     * The output of the hat. Used when defining reporters.
     */
    private Object output;

    /**
     * Whether this hat is running. If set to false during run(), stops the hat from running.
     */
    private boolean running;

    /**
     * Creates a new Hat with a core.
     */
    public Hat(CustomHat hat, Script script) {
        super(hat, null);
        this.script = script;
        this.commands = new ArrayList<>();
        this.variables = new ArrayList<>();
        running = false;
    }

    /**
     * Returns a special hat that is for custom definitions of commands and reporters.
     * @return the hat.
     */
    public static Hat defineTemplateHat(Script script){
        return new Hat(null, script);
    }

    /**
     * Runs the Hat.
     *
     * @throws VisionException if there is an error running any of the Commands in the Hat.
     */
    public void run() throws VisionException {
        running = true;
        for(Command command:commands){
            if(!running){
                return;
            }
            command.run();
        }
        running = false;
    }

    public Script getScript() {
        return script;
    }

    /**
     * Adds a Command to the Hat.
     * @param command the Command to add.
     */
    public void addCommand(Command command){
        commands.add(command);
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    @Override
    public CodeRange getRange() {
        if (commands == null || commands.isEmpty()) {
            return null;
        }
        return CodeRange.between(commands.get(0).getRange().startLocation(), commands.get(commands.size() - 1).getRange().endLocation());
    }

    /**
     * Stops the hat from continuing to run.
     */
    public void stop(){
        running = false;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }
}
