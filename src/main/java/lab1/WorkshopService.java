package lab1;

// UML: används av SimulationService (dependency).
// Har association till Workshop<Volvo240>.
public class WorkshopService {
    private final Workshop<Volvo240> workshop;
    private final double workshopX;
    private final double workshopY;
    private final double loadRadius;

    public WorkshopService(Workshop<Volvo240> workshop, double workshopX, double workshopY, double loadRadius) {
        this.workshop = workshop;
        this.workshopX = workshopX;
        this.workshopY = workshopY;
        this.loadRadius = loadRadius;
    }

    public void tryReceive(Vehicle vehicle) {
        // Endast Volvo240 tas emot i denna workshop.
        if (!(vehicle instanceof Volvo240 volvo)) return;

        double dx = volvo.getx() - workshopX;
        double dy = volvo.gety() - workshopY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < loadRadius) {
            boolean loaded = workshop.recieveCar(volvo);
            if (loaded) {
                // Sätt bilens position samma som workshopX och worksShopY
                volvo.stopEngine();
                volvo.setPosition(workshopX, workshopY);
            }
        }
    }
}
