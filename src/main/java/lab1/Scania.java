package lab1;

import lab1.interfaces.Ramp;

import java.awt.*;

public class Scania extends Vehicle{

    private final Ramp ramp = new Ramp();

    private double rampAngle = 0;

    public Scania(){
        super(2, 500, Color.pink, "Scania");
    }

    public double getRampAngle(){
        return rampAngle;
    }

    public void lowerramp(){
            if (getCurrentSpeed() == 0){
                ramp.lower();
            }
        }
    public void raiseramp(){
        if (getCurrentSpeed() == 0){
            ramp.raise();
        }
    }



    @Override
    public void gas(double amount){
        if (ramp.isdown()) return;
        super.gas(amount);
    }



    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

    // 90 km/h är maxhastighet
    @Override
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, 90);
    }

    @Override
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

}
