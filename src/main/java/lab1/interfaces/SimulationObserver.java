package lab1.interfaces;

import lab1.Vehicle;

import java.util.List;

// Observer för model-uppdateringar från simuleringen.
public interface SimulationObserver {
    void onSimulationUpdated(List<Vehicle> vehicles);

}

