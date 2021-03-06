package com.jlogical.vision.compiler.script;

import com.jlogical.vision.compiler.exceptions.VisionException;
import com.jlogical.vision.compiler.script.elements.Hat;

import java.util.ArrayList;

/**
 * Stores the compiled information for a Project. Cannot be back-compiled. Stored as a .vis file.
 */
public class Script {
    /**
     * The compilation log associated with compiling this Script.
     */
    private String compileLog;

    /**
     * The output log from the project. Occurs when the 'print []' command is called.
     */
    private String outputLog;

    /**
     * List of Hats in the Script.
     */
    private ArrayList<Hat> hats;

    /**
     * List of global Variables in the Script.
     */
    private ArrayList<Variable> variables;

    /**
     * Whether this Script was successfully compiled.
     */
    private boolean succeeded;

    /**
     * Creates a new Script.
     */
    public Script(String compileLog, ArrayList<Hat> hats) {
        this.compileLog = compileLog != null ? compileLog : "";
        this.hats = hats != null ? hats : new ArrayList<>();
        this.variables = new ArrayList<>();
        outputLog = "";
        succeeded = true;
    }

    /**
     * @return a blank Script.
     */
    public static Script blank(){
        return new Script("", null);
    }

    /**
     * Returns a Script that only has a compileScript attached due to failure to compile.
     *
     * @return the Script.
     */
    public static Script failedScript(String compileLog) {
        Script script = new Script(compileLog, null);
        script.succeeded = false;
        return script;
    }

    /**
     * Starts a Hat with the given core and provides it with the given inputs.
     * @param core the core of the Hat to run.
     * @param inputs the inputs of the Hat.
     */
    public void start(String core, Object... inputs) throws VisionException{
        for(Hat hat: hats){
            if(hat.getCore().equals(core)){
                hat.run(inputs);
            }
        }
    }

    /**
     * Starts a Hat with the given core.
     * @param core the core of the Hat to run.
     *
     * @throws VisionException if there is an error running any of the code in the Hat.
     */
    public void start(String core) throws VisionException {
        start(core, (Object[]) null);
    }

    /**
     * Starts the Script with the 'when started' Hat.
     *
     * @throws VisionException if there is an error running any of the code in the Hat.
     */
    public void start() throws VisionException{
        start("when started");
    }

    /**
     * Appends the given log into the output log. Adds a new line at the end.
     * @param log the log to append.
     */
    public void appendOutputLog(String log){
        if(outputLog.equals("")){
            outputLog += log;
        }else{
            outputLog += "\n" + log;
        }
    }

    public String getCompileLog() {
        return compileLog;
    }

    public boolean succeeded() {
        return succeeded;
    }

    public ArrayList<Hat> getHats() {
        return hats;
    }

    public void setHats(ArrayList<Hat> hats) {
        this.hats = hats;
    }

    public String getOutputLog() {
        return outputLog;
    }

    public void setCompileLog(String compileLog) {
        this.compileLog = compileLog;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }
}
