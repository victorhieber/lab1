import java.awt.*;

public class Volvo240 extends car {

    private final static double trimFactor = 1.25;

    public Volvo240() {
        // Anropar Car-konstruktorn med Volvos specifika värden
        super(4, 100, Color.black, "Volvo240");
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }

    @Override
    public void incrementSpeed(double amount) {
        // Volvons logik: får inte gå över motorstyrkan (Math.min)
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
    }

    @Override
    public void decrementSpeed(double amount) {
        // Volvons logik: får inte gå under 0 (Math.max)
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
}