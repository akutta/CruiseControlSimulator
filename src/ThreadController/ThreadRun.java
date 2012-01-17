/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadController;

/**
 *
 * @author Drew
 * 
**/

/****************************************************\
 * See Example.java for implementation of ThreadRun *
\****************************************************/
public abstract class ThreadRun extends Thread {

    @Override
    public abstract void run();

    public abstract void parse();

    public final void finishedRun() {
        runFinished = true;
        ThreadManager.getManager().threadFinished();
    }

    public final void finishedParse() {
        parsed = true;
        ThreadManager.getManager().parseFinished();
    }
    
    protected boolean parsing = false;
    protected boolean parsed = false;
    protected boolean runFinished = false;
    protected boolean running = false;
    protected double timeInitialized = 0.0;
    protected double timeFinalized = 0.0;
    protected Integer numTimesRan = 0;
    protected Integer threadID = 0;

    public Integer getNumTimesRan() {
        return numTimesRan;
    }

    public void setNumTimesRan(Integer numTimesRan) {
        this.numTimesRan = numTimesRan;
    }

    public boolean isParsed() {
        return parsed;
    }

    public void setParsed(boolean parsed) {
        this.parsed = parsed;
    }

    public boolean isParsing() {
        return parsing;
    }

    public void setParsing(boolean parsing) {
        this.parsing = parsing;
    }

    public boolean isRunFinished() {
        return runFinished;
    }

    public void setRunFinished(boolean runFinished) {
        this.runFinished = runFinished;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Integer getThreadID() {
        return threadID;
    }

    public void setThreadID(Integer threadID) {
        this.threadID = threadID;
    }

    public double getTimeFinalized() {
        return timeFinalized;
    }

    public void setTimeFinalized(double timeFinalized) {
        this.timeFinalized = timeFinalized;
    }

    public double getTimeInitialized() {
        return timeInitialized;
    }

    public void setTimeInitialized(double timeInitialized) {
        this.timeInitialized = timeInitialized;
    }
}