package lab1.interfaces;

import lab1.Vehicle;

import java.util.List;

// UML: interface som services beror på (DIP).
public interface VehicleStore {
    List<Vehicle> getAll();
    boolean add(Vehicle vehicle);
    Vehicle removeLast();
    int size();
    int capacity();

    default boolean isFull() {
        return size() >= capacity();
    }

    default boolean isEmpty() {
        return size() == 0;
    }
}
