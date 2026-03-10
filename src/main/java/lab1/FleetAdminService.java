package lab1;

import lab1.interfaces.VehicleStore;

import java.util.Random;

// Hanterar Add/Remove-regler för flottan (GUI-knapparna).
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
        // No-op när lagret är fullt.
        if (store.isFull()) {
            return false;
        }
        int choice = random.nextInt(3);
        double spawnY = 100 + store.size() * 60.0;
        Vehicle vehicle;
        if (choice == 0) {
            vehicle = vehicleFactory.createVolvo(0, spawnY);
        } else if (choice == 1) {
            vehicle = vehicleFactory.createSaab(0, spawnY);
        } else {
            vehicle = vehicleFactory.createScania(0, spawnY);
        }
        initializeNewVehicle(vehicle);
        return addGivenCar(vehicle);
    }

    public boolean addGivenCar(Vehicle vehicle) {
        // Används om man i framtiden vill välja biltyp från t.ex. en dropdown.
        if (store.isFull()) {
            return false;
        }
        return store.add(vehicle);
    }

    public Vehicle removeCar() {
        // No-op när lagret är tomt.
        if (store.isEmpty()) {
            return null;
        }
        // tar bort senast tillagda bil.
        return store.removeLast();
    }

    private void initializeNewVehicle(Vehicle vehicle) {
        // Samma riktning som startflottan.
        vehicle.turnRight();
    }
}
