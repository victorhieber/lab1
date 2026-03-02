package lab1;

import lab1.interfaces.TurboCapable;
import lab1.state.TurboOffState;
import lab1.state.TurboOnState;
import lab1.state.TurboState;

import java.awt.*;

// UML: generalisering från Vehicle + realisering av TurboCapable.
public class Saab95 extends Vehicle implements TurboCapable {
    private TurboState turboState = new TurboOffState();

    public Saab95(){
        super(2, 125, Color.red, "Saab95");
    }
    public void setTurboOn(){
        turboState = new TurboOnState();
    }
    public void setTurboOff(){
        turboState = new TurboOffState();
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01 * turboState.multiplier();
    }

    @Override
    public void incrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    @Override
    public void decrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }
}
