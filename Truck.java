import java.awt.*;

public abstract class Truck extends Vehicle{
    protected Truck(int nrDoors, double enginePower, Color color, String modelName){
        super(nrDoors, enginePower, color, modelName);
    }
}