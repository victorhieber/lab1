package lab1;

import lab1.interfaces.SimulationObserver;
import lab1.interfaces.SimulationSubject;
import lab1.interfaces.VehicleStore;

import java.util.ArrayList;
import java.util.List;



public class SimulationService implements SimulationSubject {
    // UML: association till store (hämtar alla fordon härifrån).
    private final VehicleStore store;
    // UML: dependency/usage till krockregler.
    private final CollisionService collisionService;
    // UML: dependency/usage till verkstadsregler.
    private final WorkshopService workshopService;
    private final List<SimulationObserver> observers = new ArrayList<>();

    public SimulationService(
            VehicleStore store,
            CollisionService collisionService,
            WorkshopService workshopService
    ) {
        this.store = store;
        this.collisionService = collisionService;
        this.workshopService = workshopService;
    }

    public void tick(){
        //Flytta
        //Kolla väggkrock
        //Testa workshop recieve
        for (Vehicle v: store.getAll()){
            // Parkerade bilar i verkstaden ska inte röra sig.
            if (workshopService.isParked(v)) {
                workshopService.tryReceive(v);
                continue;
            }
            v.move();
            collisionService.handleWalls(v);
            workshopService.tryReceive(v);
        }
        notifyObservers();

    }
    public List<Vehicle> getVehicles() {
        return store.getAll();
    }

    @Override
    public void addObserver(SimulationObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(SimulationObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        List<Vehicle> snapshot = store.getAll();
        for (SimulationObserver observer : observers) {
            observer.onSimulationUpdated(snapshot);
        }
    }
}
