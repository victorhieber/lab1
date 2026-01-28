import java.awt.*;

public abstract class car implements Movable{
    // PRIVATE: Dessa kan bara läsas/ändras inuti denna klass.
    private int nrDoors;
    private double enginePower;
    private Color color;
    private String modelName;
    public double currentSpeed; // The current speed of the car

    private double x = 0;
    private double y = 0;
    private int direction = 0; // 0=Nord, 1=Öst, 2=Syd, 3=Väst

    @Override
    public void move(){
        if (direction == 0)  { y += getCurrentSpeed(); }
        else if (direction == 1)  { x += getCurrentSpeed(); }
        else if (direction == 2)  { y -= getCurrentSpeed(); }
        else if (direction == 3)  { x -= getCurrentSpeed(); }
    }

    @Override
    public void turnRight() {
        switch (direction) {
            case 0 -> direction = 1;
            case 1 -> direction = 2;
            case 2 -> direction = 3;
            case 3 -> direction = 0;
        }
    }

    public void turnLeft() {
        switch (direction) {
            case 0 -> direction = 3;
            case 1 -> direction = 0;
            case 2 -> direction = 1;
            case 3 -> direction = 2;
        }
    }


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

    public String getModelName() {
        return this.modelName;
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

    private void incrementSpeed(double amount) {
        // Logiken är nu gemensam för ALLA bilar
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
    }

    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    //Gas och Brake är samma för båda
    public void gas(double amount){
        if (amount >= 0 && amount <= 1) {
        incrementSpeed(amount);
        }
        else{
            System.out.println("Felaktigt värde");
        }
    }
    public void brake(double amount){
        if (amount >= 0 && amount <= 1) {
            decrementSpeed(amount);
        }
        else{
            System.out.println("Felaktigt värde");
        }
    }
}


