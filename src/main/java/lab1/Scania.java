package lab1;

import lab1.interfaces.BedCapable;

import java.awt.*;

// UML: generalisering från Vehicle + realisering av BedCapable.
public class Scania extends Vehicle implements BedCapable {

    private final Ramp ramp = new Ramp();
    private double rampAngle = 0;

    public Scania() {
        super(2, 500, Color.pink, "Scania");
    }

    public Scania(double x, double y) {
        super(2, 500, Color.pink, "Scania", x, y);
    }

    public double getRampAngle() {
        return rampAngle;
    }

    public void lowerramp() {
        if (getCurrentSpeed() == 0) {
            ramp.lower();
        }
    }

    public void raiseramp() {
        if (getCurrentSpeed() == 0) {
            ramp.raise();
        }
    }

    //metoder så Scania matchar BedCapable-kontraktet.
    @Override
    public void raiseBed() {
        raiseramp();
    }

    @Override
    public void lowerBed() {
        lowerramp();
    }

    @Override
    public void gas(double amount) {
        if (ramp.isup()) return;
        super.gas(amount);
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }

    @Override
    public void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, 90);
    }

    @Override
    public void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
}
