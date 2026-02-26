package lab1;

import lab1.interfaces.BedCapable;
import lab1.interfaces.TurboCapable;
import lab1.interfaces.VehicleStore;

// Samlar alla "kommandon" mot fordon. Istället för i CarController
public class VehicleCommandService {
    // UML: association till VehicleStore.
    private final VehicleStore store;

    public VehicleCommandService(VehicleStore store) {
        this.store = store;
    }

    public void gasAll(double amount) {
        for (Vehicle v : store.getAll()) {
            v.gas(amount);
        }
    }

    public void brakeAll(double amount) {
        for (Vehicle v : store.getAll()) {
            v.brake(amount);
        }
    }

    public void startAll() {
        for (Vehicle v : store.getAll()) {
            v.startEngine();
        }
    }

    public void stopAll() {
        for (Vehicle v : store.getAll()) {
            v.stopEngine();
        }
    }

    // UML: dependency till interface TurboCapable.
    public void turboOnAll() {
        for (Vehicle v : store.getAll()) {
            if (v instanceof TurboCapable turbo) {
                turbo.setTurboOn();
            }
        }
    }

    public void turboOffAll() {
        for (Vehicle v : store.getAll()) {
            if (v instanceof TurboCapable turbo) {
                turbo.setTurboOff();
            }
        }
    }

    // UML: dependency till interface BedCapable.
    public void raiseBedsAll() {
        for (Vehicle v : store.getAll()) {
            if (v instanceof BedCapable bed) {
                bed.raiseBed();
            }
        }
    }

    public void lowerBedsAll() {
        for (Vehicle v : store.getAll()) {
            if (v instanceof BedCapable bed) {
                bed.lowerBed();
            }
        }
    }
}
