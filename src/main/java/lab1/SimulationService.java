package lab1;

import lab1.interfaces.VehicleStore;

import java.util.List;



public class SimulationService {
    // UML: association till store (hämtar alla fordon härifrån).
    private final VehicleStore store;
    // UML: dependency/usage till krockregler.
    private final CollisionService collisionService;
    // UML: dependency/usage till verkstadsregler.
    private final WorkshopService workshopService;

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

    }
    public List<Vehicle> getVehicles() {
        return store.getAll();
    }
}
