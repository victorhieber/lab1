package lab1;

import java.util.List;

// Factory Method: skapar fordon utan att bootstrap kod använder "new" överallt.
public interface VehicleFactory {
    Vehicle createVolvo();
    Vehicle createSaab();
    Vehicle createScania();
    List<Vehicle> createInitialFleet();
}

