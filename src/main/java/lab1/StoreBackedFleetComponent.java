package lab1;

import lab1.interfaces.VehicleStore;

import java.util.function.Consumer;

// Composite-adapter: itererar alltid aktuella fordon i store.
public class StoreBackedFleetComponent implements FleetComponent {
    private final VehicleStore store;

    public StoreBackedFleetComponent(VehicleStore store) {
        this.store = store;
    }

    @Override
    public void forEachVehicle(Consumer<Vehicle> action) {
        for (Vehicle vehicle : store.getAll()) {
            action.accept(vehicle);
        }
    }
}

