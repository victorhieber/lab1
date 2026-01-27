import java.awt.*;

public class Saab95 extends car implements Movable{
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
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, 1);
    }

    @Override
    public void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
}
