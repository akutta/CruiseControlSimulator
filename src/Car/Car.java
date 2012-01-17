/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Car;

/**
 *
 * @author Drew
 */
public class Car {
    
    public Car() {
        wheel = Wheel.getWheel();
        engine = Engine.getEngine();
    }
    
    public Engine getEngine() { return engine; }
    public Wheel getWheel() { return wheel; }
    
    private Engine engine;
    private Wheel wheel;
}
