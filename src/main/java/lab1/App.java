package lab1;

import lab1.interfaces.VehicleStore;

import javax.swing.SwingUtilities;
import java.util.List;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VehicleFactory vehicleFactory = new DefaultVehicleFactory();
            List<Vehicle> fleet = vehicleFactory.createInitialFleet();

            VehicleStore store = new InMemoryVehicleStore();
            VehicleGroup fleetGroup = new VehicleGroup();
            for (Vehicle vehicle : fleet) {
                store.add(vehicle);
                fleetGroup.add(new VehicleLeaf(vehicle));
            }

            Workshop<Volvo240> workshop = new VolvoWorkshop(10);
            CollisionService collisionService = new CollisionService(0, 0, 800, 560);
            WorkshopService workshopService = new WorkshopService(workshop, 300, 100, 10);
            SimulationService simulationService = new SimulationService(store, collisionService, workshopService);
            VehicleCommandService commandService = new VehicleCommandService(fleetGroup);

            CarView view = new CarView("CarSim 1.0", null);
            CarController controller = new CarController(view, simulationService, commandService);
            view.setController(controller);
            controller.startTimer();
        });
    }
}

