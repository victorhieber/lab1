package lab1;

import lab1.interfaces.TurboCapable;

import java.awt.*;

public class Saab95 extends Vehicle implements TurboCapable {
    private boolean turboOn = false;

    public Saab95(){
        super(2, 125, Color.red, "Saab95");
    }
    public void setTurboOn(){
        turboOn = true;
    }
    public void setTurboOff(){
        turboOn = false;
    }

    @Override
    public double speedFactor() {
        double turbo = turboOn ? 1.3 : 1.0;
        return getEnginePower() * 0.01 * turbo;
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
