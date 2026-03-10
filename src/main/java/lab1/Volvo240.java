package lab1;

import java.awt.*;

public class Volvo240 extends Vehicle {

    private final static double trimFactor = 1.25;

    public Volvo240() {
        // Anropar Car-konstruktorn med Volvos specifika värden
        super(4, 100, Color.black, "Volvo240");
    }

    public Volvo240(double x, double y) {
        super(4, 100, Color.black, "Volvo240", x, y);
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }

    @Override
    public void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
    }

    @Override
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
}