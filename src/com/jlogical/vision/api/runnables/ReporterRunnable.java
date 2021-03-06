package com.jlogical.vision.api.runnables;


import com.jlogical.vision.compiler.exceptions.VisionException;

/**
 * Lambda for running and getting values from Reporters.
 */
public interface ReporterRunnable {

    /**
     * @param p the Parameters for the Reporter.
     * @return the value.
     */
    Object getValue(ReporterParameters p) throws VisionException;
}
