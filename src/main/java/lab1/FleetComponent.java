package lab1;

import java.util.function.Consumer;

// Composite-kontrakt: behandla en bil eller grupp på samma sätt.
public interface FleetComponent {
    void forEachVehicle(Consumer<Vehicle> action);
}

