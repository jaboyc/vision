package com.jlogical.vision.api.runnables;

import com.jlogical.vision.compiler.exceptions.VisionException;

/**
 * A lambda for running Commands.
 */
public interface CommandRunnable<T extends CommandParameters> {
    /**
     * Action to run the Command with.
     * @param p the Parameters of the Command.
     * @throws VisionException when an error running the code has occurred.
     */
    void run(T p) throws VisionException;
}
