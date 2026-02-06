import java.util.ArrayList;
import java.util.List;

public class CarrierCargo {
    private final List<Vehicle> cars = new ArrayList<>();

    private final int maxcars;
    public CarrierCargo(int maxcars){
        this.maxcars = maxcars;
    }
    public boolean isfull(){
        return cars.size() >= maxcars;
    }
    public boolean addcar(Vehicle car){
        if (isfull()) return false;
        cars.add(car); return true;
    }
    public Vehicle unload(){
        if(cars.isEmpty()) return null;
        return cars.remove(cars.size() -1);
    }
    public void syncPositions(double x, double y){
        for (Vehicle car : cars){
            car.setPosition(x,y);
        }
    }

}
