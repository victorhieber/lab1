package lab1;

import java.util.ArrayList;
import java.util.List;

public class DefaultVehicleFactory implements VehicleFactory {
    @Override
    public Vehicle createVolvo() {
        return new Volvo240();
    }

    @Override
    public Vehicle createVolvo(double x, double y) {
        return new Volvo240(x, y);
    }

    @Override
    public Vehicle createSaab() {
        return new Saab95();
    }

    @Override
    public Vehicle createSaab(double x, double y) {
        return new Saab95(x, y);
    }

    @Override
    public Vehicle createScania() {
        return new Scania();
    }

    @Override
    public Vehicle createScania(double x, double y) {
        return new Scania(x, y);
    }

    @Override
    public List<Vehicle> createInitialFleet() {
        List<Vehicle> fleet = new ArrayList<>();

        Vehicle volvo = createVolvo(0, 100);
        Vehicle saab = createSaab(0, 200);
        Vehicle scania = createScania(0, 300);

        volvo.turnRight();
        saab.turnRight();
        scania.turnRight();

        fleet.add(volvo);
        fleet.add(saab);
        fleet.add(scania);
        return fleet;
    }
}

