/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Car;

/**
 *
 * @author Drew
 */
public class Engine {
    
    
    private static Engine engine;
    private Double horsepower;
    private Double torque;
    private Double drivetrainLossFactor;
    
    private Engine() {
    }
    
    public static Engine getEngine() {
        if ( engine == null )
            engine = new Engine();
        return engine;
    }
    
    public Double getDrivetrainLossFactor() {
        return drivetrainLossFactor;
    }

    public void setDrivetrainLossFactor(Double drivetrainLossFactor) {
        this.drivetrainLossFactor = drivetrainLossFactor;
    }

    public Double getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Double horsepower) {
        this.horsepower = horsepower;
    }

    public Double getTorque() {
        return torque;
    }

    public void setTorque(Double torque) {
        this.torque = torque;
    }
}
