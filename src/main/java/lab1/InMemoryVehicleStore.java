package lab1;
import lab1.interfaces.VehicleStore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Lagrar fordon i minnet (lista).
public class InMemoryVehicleStore implements VehicleStore {
    // UML: aggregation - store håller 0..* Vehicle.
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final int maxCars;

    public InMemoryVehicleStore() {
        this(10);
    }

    public InMemoryVehicleStore(int maxCars) {
        this.maxCars = maxCars;
    }

    @Override
    public List<Vehicle> getAll() {
        // Returnerar en lista som inte går att modifiera utifrån
        return Collections.unmodifiableList(vehicles);
    }

    @Override
    public boolean add(Vehicle vehicle) {
        if (isFull()) {
            return false;
        }
        vehicles.add(vehicle);
        return true;
    }

    @Override
    public Vehicle removeLast() {
        if (vehicles.isEmpty()) {
            return null;
        }
        return vehicles.remove(vehicles.size() - 1);
    }

    @Override
    public int size() {
        return vehicles.size();
    }

    @Override
    public int capacity() {
        return maxCars;
    }
}
