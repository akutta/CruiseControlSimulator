/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ThreadController;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Drew
 */
public class ThreadManager extends Thread {

    private static ThreadManager threadManager;
    private Integer numThreads;
    private ArrayList<ThreadRun> threads;
    private boolean bFinishedSimulation;
    private Integer numRunningThreads;
    private static Integer MAX_THREADS = 3;
    private boolean bLockThreads;

    private ThreadManager() {
        bFinishedSimulation = false;
        numThreads = 0;
        numRunningThreads = 0;

        // Dynamically determine the maximum amount of threads
        // that can be run on the cpu at one time.
        //
        // Not setting to availableProcessors() - 1 
        // since the main ThreadManager thread will be primarily
        // calling sleep freeing the cpu for work on other threads
        MAX_THREADS = Runtime.getRuntime().availableProcessors();
        System.err.println("Maximum Number of Threads Available:\t" + MAX_THREADS);
        threads = new ArrayList<ThreadRun>(10);

    }

    //
    // Singleton
    //
    public static ThreadManager getManager() {
        if (threadManager == null) {
            threadManager = new ThreadManager();
        }
        return threadManager;
    }

    // Adds threads to the thread queue
    // Can be added at any time, but should have at least one added 
    // before starting ThreadManager thread
    public void addThread(ThreadRun newThread) {
        Debug.out.println("Adding new Thread: " + numThreads);
        newThread.threadID = numThreads;
        threads.add(newThread);
        numThreads++;
    }

    // Called by ThreadRun
    //
    // Should add a callback functionality to notify main program if wanted
    public void threadFinished() {
        parse();
    }

    // Called by ThreadRun
    //
    // Should add a callback functionality to notify main program if wanted
    public void parseFinished() {
        cleanup();
    }

    private void cleanup() {
        // Prevent deadlocks!!!
        // Multiple threads can call this at the same time
        // Considered just returning, but might cause a wierd case
        // that leaves a single thread attempting to be run but has already been ran
        while (bLockThreads) {
            threadSleep(10);
        }

        // More deadlock prevention
        bLockThreads = true;

        // Iterate through all presently queued threads and 
        // remove threads that are finished outputting data
        for (Integer i = threads.size() - 1; i >= 0; i--) {
            ThreadRun cur = threads.get(i);
            if (cur.parsed && cur.runFinished) {
                Debug.out.println("Removing " + cur.threadID);
                threads.remove(cur);
                
                // Notify Thread Manager that we can start a new thread
                if (numRunningThreads > 0) {
                    numRunningThreads--;
                }
            }
        }

        bLockThreads = false;
    }

    private void parse() {
        // Iterate through all currently queued threads
        // and check to see if they need to be parsed
        for (Integer i = threads.size() - 1; i >= 0; i--) {
            ThreadRun cur = threads.get(i);
            if (cur.runFinished && !cur.parsed && !cur.parsing) {
                cur.parsing = true;
                cur.parse();
            }
        }
    }

    private ThreadRun getNextThread() {

        // Sanity Checks
        if (numRunningThreads >= MAX_THREADS || threads.isEmpty()) {
            return null;
        }

        ThreadRun nextThread = null;
        Integer threadIndex = -1;

        // At most there would be 12 threads on modern processors.  
        // A list of twelve does not need to do anything more fancy than
        // a linear search.
        //
        // Could add in a method that when the 
        do {

            // Since other threads are running concurrently
            // make sure the threads doesn't become in use while we are 
            // looking for a new thread.
            // 
            // Doesn't really slow down performance.
            // Also kicks us out of an infinite loop
            // that can occur here
            //
            // TO-DO:  Tweak while conditions to prevent infinite loop
            if (bLockThreads) {
                return null;
            }

            threadIndex++;
            ThreadRun tmp = threads.get(threadIndex);

            // Should technically only need to check .running as it is never set back to false
            // But for sanity might as well check
            if (tmp.running || tmp.parsed || tmp.runFinished) {
                // No new thread to run
                tmp = null;
            }

            nextThread = tmp;
        } while (threadIndex + 1 < threads.size() && nextThread == null);

        return nextThread;
    }

    @Override
    public void run() {
        while (!bFinishedSimulation) {

            // The sleep can be reduced to 10.
            // Set at 100 to free the CPU more.
            //
            // Slept here instead of at end since continue is being used
            threadSleep(100);

            // Semi treat as recursive except do continue instead of return.
            // This is the base case
            if (threads.isEmpty()) {
                Debug.out.println("Finished Simulation");
                bFinishedSimulation = true;
                continue;
            }

            // Make sure we have a new thread to start
            ThreadRun curRunningThread = getNextThread();
            if (curRunningThread == null) {
                continue;
            }

            // Start the thread
            Debug.out.println("Starting Thread [" + curRunningThread.threadID + "]");
            numRunningThreads++;
            curRunningThread.running = true;
            curRunningThread.start();
        }
    }

    //
    // I Hate looking at the try...catch block in actual code
    // This solves that problem.
    //
    private void threadSleep(int timeToSleep) {
        try {
            sleep(timeToSleep);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
