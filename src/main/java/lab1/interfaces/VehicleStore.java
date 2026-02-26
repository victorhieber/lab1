package lab1.interfaces;

import lab1.Vehicle;

import java.util.ArrayList;
import java.util.List;

// UML: interface som services beror på (DIP).
public interface VehicleStore {
    List<Vehicle> getAll();
    void add(Vehicle vehicle);
}
