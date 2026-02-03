import java.awt.*;

public class Scania extends car{

    private double rampAngle = 0;

    public Scania(){
        super(2, 500, Color.pink, "Scania");
    }

    public double getRampAngle(){
        return rampAngle;
    }

    public void raiseRamp(double degrees){
        if (getCurrentSpeed() != 0) return;
        if (degrees < 0) return;
        rampAngle = Math.min(rampAngle + degrees, 70);
    }

    public void lowerRamp(double degrees){
        if (getCurrentSpeed() != 0) return;
        if (degrees < 0) return;
        rampAngle = Math.max(rampAngle - degrees, 0);
    }

    @Override
    public void gas(double amount){
        if (getRampAngle() > 0) return;
        super.gas(amount);
    }



    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
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
