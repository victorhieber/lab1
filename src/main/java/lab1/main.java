package lab1;

import javax.swing.SwingUtilities;
import lab1.interfaces.VehicleStore;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1) Lagring av alla fordon (UML: VehicleStore <- InMemoryVehicleStore)
            VehicleStore store = new InMemoryVehicleStore();

            Vehicle volvo = new Volvo240();
            Vehicle saab = new Saab95();
            Vehicle scania = new Scania();

            //Samma startläge som tidigare
            volvo.turnRight();
            saab.turnRight();
            scania.turnRight();

            volvo.setPosition(0, 100);
            saab.setPosition(0, 200);
            scania.setPosition(0, 300);

            store.add(volvo);
            store.add(saab);
            store.add(scania);

            // 2) Initiera alla "tjänster" (UML: associations/dependencies)
            Workshop<Volvo240> workshop = new VolvoWorkshop(10);
            CollisionService collisionService = new CollisionService(0, 0, 800, 560);
            WorkshopService workshopService = new WorkshopService(workshop, 300, 100, 10);
            SimulationService simulationService = new SimulationService(store, collisionService, workshopService);
            VehicleCommandService commandService = new VehicleCommandService(store);

            // 3) View + Controller (UML: CarView realiserar SimulationView)
            CarView view = new CarView("CarSim 1.0", null);
            CarController controller = new CarController(view, simulationService, commandService);

            // Koppla in controller i view (enklast med nuvarande kod)
            view.carC = controller;

            controller.startTimer();
        });
    }
}
