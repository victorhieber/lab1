package lab1;

import lab1.interfaces.BedCapable;
import lab1.interfaces.TurboCapable;
import lab1.interfaces.VehicleStore;

// Samlar alla "kommandon" mot fordon. Istället för i CarController
public class VehicleCommandService {
    private final FleetComponent fleet;

    // UML: association till Composite-roten.
    public VehicleCommandService(FleetComponent fleet) {
        this.fleet = fleet;
    }

    // Behålls för enkel migration från tidigare design.
    public VehicleCommandService(VehicleStore store) {
        VehicleGroup group = new VehicleGroup();
        for (Vehicle vehicle : store.getAll()) {
            group.add(new VehicleLeaf(vehicle));
        }
        this.fleet = group;
    }

    public void gasAll(double amount) {
        fleet.forEachVehicle(v -> v.gas(amount));
    }

    public void brakeAll(double amount) {
        fleet.forEachVehicle(v -> v.brake(amount));
    }

    public void startAll() {
        fleet.forEachVehicle(Vehicle::startEngine);
    }

    public void stopAll() {
        fleet.forEachVehicle(Vehicle::stopEngine);
    }

    // UML: dependency till interface TurboCapable.
    public void turboOnAll() {
        fleet.forEachVehicle(v -> {
            if (v instanceof TurboCapable turbo) {
                turbo.setTurboOn();
            }
        });
    }

    public void turboOffAll() {
        fleet.forEachVehicle(v -> {
            if (v instanceof TurboCapable turbo) {
                turbo.setTurboOff();
            }
        });
    }

    // UML: dependency till interface BedCapable.
    public void raiseBedsAll() {
        fleet.forEachVehicle(v -> {
            if (v instanceof BedCapable bed) {
                bed.raiseBed();
            }
        });
    }

    public void lowerBedsAll() {
        fleet.forEachVehicle(v -> {
            if (v instanceof BedCapable bed) {
                bed.lowerBed();
            }
        });
    }
}
