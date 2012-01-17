/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Car;

/**
 *
 * @author Drew
 */
public class Wheel {
    private Wheel () {
    }
    
    static public Wheel getWheel() {
        if ( wheel == null )
            wheel = new Wheel();
        return wheel;
    }
    
    static private Wheel wheel;
}
