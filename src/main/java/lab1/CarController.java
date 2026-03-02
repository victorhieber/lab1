package lab1;

import lab1.interfaces.SimulationObserver;
import lab1.interfaces.SimulationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Tunn controller: koordinerar bara flödet.
public class CarController {
    private final int delay = 50;
    private final Timer timer;

    // UML: association till view-abstraktion (DIP).
    private final SimulationView view;
    // UML: association till SimulationSerivce.
    private final SimulationService simulationService;
    // UML: association till VehicleCommandService.
    private final VehicleCommandService commandService;

    public CarController(
            SimulationView view,
            SimulationService simulationService,
            VehicleCommandService commandService
    ) {
        this.view = view;
        this.simulationService = simulationService;
        this.commandService = commandService;
        this.timer = new Timer(delay, new TickListener());

        if (view instanceof SimulationObserver observer) {
            this.simulationService.addObserver(observer);
        }
    }

    public void startTimer() {
        timer.start();
    }

    // Tick: simulera och rendera.
    private class TickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            simulationService.tick();
        }
    }

    public void onGas(int amount) {
        commandService.gasAll(amount / 100.0);
    }

    public void onBrake(int amount) {
        commandService.brakeAll(amount / 100.0);
    }

    public void onStartAll() {
        commandService.startAll();
    }

    public void onStopAll() {
        commandService.stopAll();
    }

    public void onTurboOn() {
        commandService.turboOnAll();
    }

    public void onTurboOff() {
        commandService.turboOffAll();
    }

    public void onRaiseBeds() {
        commandService.raiseBedsAll();
    }

    public void onLowerBeds() {
        commandService.lowerBedsAll();
    }
}
