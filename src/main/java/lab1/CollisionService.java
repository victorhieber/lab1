package lab1;


// UML: används av SimulationService (dependency).
// Hanterar väggkollision istället för CarController.
public class CollisionService {
    private final double minX, minY, maxX, maxY;

    public CollisionService(double minX, double minY, double maxX, double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    public void handleWalls(Vehicle vehicle) {
        double x = vehicle.getx();
        double y = vehicle.gety();

        boolean hitWall = x < minX || x > maxX || y < minY || y > maxY;
        if (!hitWall) return;

        //
        vehicle.turnLeft();
        vehicle.turnLeft();
    }
}
