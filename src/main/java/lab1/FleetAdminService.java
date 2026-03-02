package lab1;

import lab1.interfaces.VehicleStore;

import java.util.Random;

// Hanterar add/remove-regler för fordonsflottan.
public class FleetAdminService {
    private final VehicleStore store;
    private final VehicleFactory vehicleFactory;
    private final Random random;

    public FleetAdminService(VehicleStore store, VehicleFactory vehicleFactory) {
        this(store, vehicleFactory, new Random());
    }

    public FleetAdminService(VehicleStore store, VehicleFactory vehicleFactory, Random random) {
        this.store = store;
        this.vehicleFactory = vehicleFactory;
        this.random = random;
    }

    public boolean addRandomCar() {
        if (store.isFull()) {
            return false;
        }
        int choice = random.nextInt(3);
        Vehicle vehicle;
        if (choice == 0) {
            vehicle = vehicleFactory.createVolvo();
        } else if (choice == 1) {
            vehicle = vehicleFactory.createSaab();
        } else {
            vehicle = vehicleFactory.createScania();
        }
        initializeNewVehicle(vehicle);
        return addGivenCar(vehicle);
    }

    public boolean addGivenCar(Vehicle vehicle) {
        if (store.isFull()) {
            return false;
        }
        return store.add(vehicle);
    }

    public Vehicle removeCar() {
        if (store.isEmpty()) {
            return null;
        }
        return store.removeLast();
    }

    private void initializeNewVehicle(Vehicle vehicle) {
        // Samma riktning som ursprungliga bilar.
        vehicle.turnRight();
        // Enkel spawn-position nära vänster kant.
        vehicle.setPosition(0, 100 + store.size() * 60.0);
    }
}

