package lab1.interfaces;

import lab1.Vehicle;

import java.util.List;

// Gemensamt lager-API för alla fordon i simuleringen.
public interface VehicleStore {
    // Read-only lista över aktuella fordon.
    List<Vehicle> getAll();
    // Returnerar false om lagret är fullt.
    boolean add(Vehicle vehicle);
    // Tar bort senast tillagda fordon (LIFO), null om tomt.
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
