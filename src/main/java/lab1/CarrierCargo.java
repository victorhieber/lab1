package lab1;

import java.util.ArrayList;
import java.util.List;

public class CarrierCargo<T extends Vehicle> {
    private final List<T> vehicles = new ArrayList<>();

    private final int maxcars;
    public CarrierCargo(int maxcars){
        this.maxcars = maxcars;
    }
    public boolean isfull(){
        return vehicles.size() >= maxcars;
    }
    public boolean addcar(T Vehicle){
        if (isfull()) return false;
        vehicles.add(Vehicle); return true;
    }
    public T unload(){
        if(vehicles.isEmpty()) return null;
        return vehicles.remove(vehicles.size() -1);
    }
    public void syncPositions(double x, double y) {
     for (T vehicle : vehicles) {
        vehicle.setPosition(x, y);
    }
}

    public int size() {
        return vehicles.size();
    }
}
