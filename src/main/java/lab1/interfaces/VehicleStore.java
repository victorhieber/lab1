package lab1.interfaces;

import lab1.Vehicle;

import java.util.List;

public interface VehicleStore {
    List<Vehicle> getAll();
    void add(Vehicle vehicle);
}

