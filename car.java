import java.awt.*;

public abstract class car { //Abstract pga trimfactor och variabler som inte är samma
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    //Sätter dessa som private för
    // att man inte ska kunna överskrida
    // dem utan man tvingas att använda
    // metoder såsom gas/brake etc


    //setter, istället för self i python har vi this
    public car(int nrDoors, double enginePower, double currentSpeed, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.currentSpeed = currentSpeed;
        this.color = color;
        this.modelName = modelName;
    }
    //getters
    public int getNrDoors(){
        return this.nrDoors;
    }

    public double getEnginePower() {
        return this.enginePower;
    }
    public double getCurrentSpeed(){
        return this.currentSpeed;
    }
    public Color getColor(){
        return this.color;
    }
    public void setColor(Color clr){
        this.color = clr;
    }
    public void startEngine(){
        this.currentSpeed = 0.1;
    }
    public void stopEngine(){
        this.currentSpeed = 0;
    }
    //Abstrakt metod för vi låter subklasserna skriva sin egen version
    public abstract double speedFactor();

    public abstract void incrementSpeed(double amount);
    public abstract void decrementSpeed(double amount);

}
