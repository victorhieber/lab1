package lab1;

import java.util.List;

// Factory Method: skapar fordon utan att bootstrap kod använder "new" överallt.
public interface VehicleFactory {
    Vehicle createVolvo();
    Vehicle createVolvo(double x, double y);
    Vehicle createSaab();
    Vehicle createSaab(double x, double y);
    Vehicle createScania();
    Vehicle createScania(double x, double y);
    List<Vehicle> createInitialFleet();
}

