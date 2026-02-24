package lab1;
import lab1.interfaces.VehicleStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Lagrar fordon i minnet (lista).
public class InMemoryVehicleStore implements VehicleStore {
    private final List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public List<Vehicle> getAll() {
        // Returnerar en lista som inte går att modifiera utifrån
        return Collections.unmodifiableList(vehicles);
    }

    @Override
    public void add(Vehicle vehicle) {
        vehicles.add(vehicle);
    }
}
