import java.awt.*;

public abstract class car {
    // PRIVATE: Dessa kan bara läsas/ändras inuti denna klass.
    private int nrDoors;
    private double enginePower;
    private Color color;
    private String modelName;
    public double currentSpeed; // The current speed of the car


    //Konstruktor, körs när en subklass skapas
    public car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.stopEngine();
    }
    //getter för nrDoors
    public int getNrDoors(){
        return this.nrDoors;
    }
    //getter för enginePower
    public double getEnginePower() {
        return this.enginePower;
    }
    //getter för current speed
    public double getCurrentSpeed(){
        return this.currentSpeed;
    }
    //getter för color
    public Color getColor(){
        return this.color;
    }
    //setter för color
    public void setColor(Color clr){
        this.color = clr;
    }

    //setter för stop engine
    public void stopEngine(){
        this.currentSpeed = 0;
    }
    //startengine och stop engine är likadan för både saab och volvo
    public void startEngine(){
        currentSpeed = 0.1;
    }

    //Vi säger att alla bilar har en speedfactor och kan gasa/bromsa
    //Men vi lämnar uträkninen åt klassen själv
    public abstract double speedFactor();
    public abstract void incrementSpeed(double amount);
    public abstract void decrementSpeed(double amount);

    //Gas och Brake är samma för båda
    public void gas(double amount){
        incrementSpeed(amount);
    }
    public void brake(double amount){
        decrementSpeed(amount);
    }
}


