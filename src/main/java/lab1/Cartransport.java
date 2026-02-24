package lab1;

import lab1.interfaces.Ramp;

import java.awt.*;

public class Cartransport extends Truck {

    private final Ramp ramp = new Ramp();
    private final CarrierCargo Cargo = new CarrierCargo(6);

    public void lowerramp(){
        if (getCurrentSpeed() == 0){
            ramp.lower();
        }
    }
    public void raiseramp(){
        if (getCurrentSpeed() == 0){
            ramp.raise();
        }
    }


    public Cartransport(){
        super(2,400, Color.black, "Car transport");
    }

    public boolean LoadCar(Vehicle car){
        if(getCurrentSpeed() != 0){
            return false;
        }
        if(ramp.isup()){
            return false;
        }
        if(distanceTO(car) > 2){
            return false;
        }
        //Står inte uppg men går inte att ladda bilar om den är full
        if(Cargo.isfull()){
            return false;
        }
        if (car instanceof Cartransport){
            return false;
        }
        boolean loaded = Cargo.addcar(car);
        if(loaded){
            car.stopEngine();
            //Sätter bilens koordinater till transportens koordinater
            car.setPosition(getx(), gety());

        }
        return loaded;
    }

    public Vehicle unloadcar(){
        if(getCurrentSpeed() !=0){
            return null;
        }
        if(ramp.isup()){
            return null;
        }
        Vehicle car = Cargo.unload();
        car.setPosition(getx(), gety() -1);
        return car;
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
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
    @Override
    public void move(){
        super.move();
        //Skickar in transportens koordinater och syncP loopar och ändrar bilarnas Pos
        Cargo.syncPositions(getx(), gety());
    }
    /*
    @Override
    public void gas(double amount){
        if (getRampAngle() > 0) return;
        super.gas(amount);
    }

     */


}

