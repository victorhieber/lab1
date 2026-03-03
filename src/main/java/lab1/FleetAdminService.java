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
        // Exempel: 1:a nya bilen hamnar vid y=100, nästa vid y=160.
        vehicle.setPosition(0, 100 + store.size() * 60.0);
    }
}
