package lab1.interfaces;

import lab1.Vehicle;

import java.util.List;

// UML: CarView realiserar detta interface, CarController har association hit.
public interface SimulationView {
    void render(List<Vehicle> vehicles);
}
