package lab1;

import java.util.function.Consumer;

// Leaf i Composite: exakt en bil.
public class VehicleLeaf implements FleetComponent {
    private final Vehicle vehicle;

    public VehicleLeaf(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void forEachVehicle(Consumer<Vehicle> action) {
        action.accept(vehicle);
    }
}

