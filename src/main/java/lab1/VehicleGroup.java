package lab1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// Composite: grupp av FleetComponent (leaf + grupper).
public class VehicleGroup implements FleetComponent {
    private final List<FleetComponent> children = new ArrayList<>();

    public void add(FleetComponent component) {
        children.add(component);
    }

    public void remove(FleetComponent component) {
        children.remove(component);
    }

    @Override
    public void forEachVehicle(Consumer<Vehicle> action) {
        for (FleetComponent child : children) {
            child.forEachVehicle(action);
        }
    }
}

