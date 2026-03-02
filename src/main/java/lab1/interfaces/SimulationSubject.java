package lab1.interfaces;

// Subject-delen i Observer-patternet.
public interface SimulationSubject {
    void addObserver(SimulationObserver observer);
    void removeObserver(SimulationObserver observer);
}

