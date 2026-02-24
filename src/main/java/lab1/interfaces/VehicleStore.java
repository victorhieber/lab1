package lab1.interfaces;

import lab1.Vehicle;

import java.util.ArrayList;
import java.util.List;

//Interface för vart alla fordon finns lagrade
public interface VehicleStore {
    List<Vehicle> getAll();
    void add(Vehicle vehicle);
}

