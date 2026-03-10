package lab1;

import java.util.HashSet;
import java.util.Set;

// UML: används av SimulationService (dependency).
// Har association till Workshop<Volvo240>.
public class WorkshopService {
    private final Workshop<Volvo240> workshop;
    private final double workshopX;
    private final double workshopY;
    private final double loadRadius;
    private final Set<Vehicle> parkedVehicles = new HashSet<>();

    public WorkshopService(Workshop<Volvo240> workshop, double workshopX, double workshopY, double loadRadius) {
        this.workshop = workshop;
        this.workshopX = workshopX;
        this.workshopY = workshopY;
        this.loadRadius = loadRadius;
    }

    public void tryReceive(Vehicle vehicle) {
        // Redan parkerad i verkstaden: bilen får inte köras iväg.
        if (parkedVehicles.contains(vehicle)) {
            vehicle.park();
            return;
        }

        // Endast Volvo240 tas emot i denna workshop.
        if (!(vehicle instanceof Volvo240 volvo)) return;

        double dx = volvo.getx() - workshopX;
        double dy = volvo.gety() - workshopY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < loadRadius) {
            boolean loaded = workshop.recieveCar(volvo);
            if (loaded) {
                parkedVehicles.add(volvo);
                volvo.park();
            }
        }
    }

    public boolean isParked(Vehicle vehicle) {
        return parkedVehicles.contains(vehicle);
    }
}
