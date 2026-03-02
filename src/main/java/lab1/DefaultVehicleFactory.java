package lab1;

import java.util.ArrayList;
import java.util.List;

public class DefaultVehicleFactory implements VehicleFactory {
    @Override
    public Vehicle createVolvo() {
        return new Volvo240();
    }

    @Override
    public Vehicle createSaab() {
        return new Saab95();
    }

    @Override
    public Vehicle createScania() {
        return new Scania();
    }

    @Override
    public List<Vehicle> createInitialFleet() {
        List<Vehicle> fleet = new ArrayList<>();

        Vehicle volvo = createVolvo();
        Vehicle saab = createSaab();
        Vehicle scania = createScania();

        volvo.turnRight();
        saab.turnRight();
        scania.turnRight();

        volvo.setPosition(0, 100);
        saab.setPosition(0, 200);
        scania.setPosition(0, 300);

        fleet.add(volvo);
        fleet.add(saab);
        fleet.add(scania);
        return fleet;
    }
}

