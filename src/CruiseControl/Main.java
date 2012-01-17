/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CruiseControl;

import ThreadController.ThreadManager;


/**
 *
 * @author akutta
 */
public class Main {

    public void run() {
        new Example();
        new Example();
        new Example();
        new Example();
        new Example();
        ThreadManager.getManager().start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Debug.out.outputDebug = true;
        Main main = new Main();
        main.run();
    }

}
