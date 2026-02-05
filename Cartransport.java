import java.awt.*;

public class Cartransport extends Truck {
    public Cartransport(){
        super(2,400, Color.black, "Car transport");
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.01;
    }
    //90 km/h är maxhastighet
    @Override
    public void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, 90);

    }

    @Override
    public void decrementSpeed(double amount) {


    }


}
