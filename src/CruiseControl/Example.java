/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CruiseControl;

import ThreadController.*;
import Utilities.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Drew
 */
class Example extends ThreadRun {

    private Time time;

    public void run() {
        time = new Time();
        Double startTime = time.getCurrentTime();
        
        while (!runFinished) {
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (time.getCurrentTime() - startTime > 10) {
                Debug.out.println("[" + threadID + "]:\t Finished Running for 10 Seconds");
                finishedRun();
            }
        }
    }

    public void parse() {
        Debug.out.println("Parsed ThreadID:  " + threadID);
        finishedParse();
    }

    public Example() {
        numTimesRan = 0;
        ThreadManager.getManager().addThread(this);
    }
}
