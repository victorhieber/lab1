package lab1;

import lab1.interfaces.VehicleStore;

import javax.swing.SwingUtilities;
import java.util.List;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1) Skapa startdata
            VehicleFactory vehicleFactory = new DefaultVehicleFactory();
            List<Vehicle> fleet = vehicleFactory.createInitialFleet();

            // 2) Bygg model-lager
            VehicleStore store = new InMemoryVehicleStore(10);
            for (Vehicle vehicle : fleet) {
                store.add(vehicle);
            }

            // 3) Bygg services (applikationslogik)
            Workshop<Volvo240> workshop = new VolvoWorkshop(10);
            CollisionService collisionService = new CollisionService(0, 0, 800, 560);
            WorkshopService workshopService = new WorkshopService(workshop, 300, 100, 10);
            SimulationService simulationService = new SimulationService(store, collisionService, workshopService);
            VehicleCommandService commandService = new VehicleCommandService(store);
            FleetAdminService fleetAdminService = new FleetAdminService(store, vehicleFactory);

            // 4) Koppla MVC
            CarView view = new CarView("CarSim 1.0", null);
            CarController controller = new CarController(view, simulationService, commandService, fleetAdminService);
            view.setController(controller);
            controller.startTimer();
        });
    }
}
