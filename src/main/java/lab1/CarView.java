package lab1;

import lab1.interfaces.SimulationObserver;
import lab1.interfaces.SimulationView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

/**
 * Fullständig vy i MVC för bilsimulatorn.
 * Vyn vidarebefordrar användarens knapptryckningar till CarController.
 **/

public class CarView extends JFrame implements SimulationView, SimulationObserver {
    private static final int X = 800;
    private static final int Y = 800;
    // Referens till controllern som hanterar all logik.
    private CarController carC;

    DrawPanel drawPanel = new DrawPanel(X, Y-240);

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");
    JButton addCarButton = new JButton("Add car");
    JButton removeCarButton = new JButton("Remove car");

    // Konstruktor
    public CarView(String framename, CarController cc){
        this.carC = cc;
        initComponents(framename);
    }

    public void setController(CarController controller) {
        this.carC = controller;
    }

    // Skyddar mot null innan controller är injicerad.
    private void withController(Consumer<CarController> action) {
        if (carC != null) {
            action.accept(carC);
        }
    }

    // Bygger hela GUI:t och kopplar alla knappar till controller-metoder.
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);



        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.add(addCarButton, 6);
        controlPanel.add(removeCarButton, 7);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        this.add(stopButton);

        // Gas påverkar alla bilar via controllern.
        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withController(c -> c.onGas(gasAmount));
            }
        });

        // Broms använder samma spinner-värde som gas.
        brakeButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withController(c -> c.onBrake(gasAmount));
            }
        }));

        // Turbo-knappar gäller endast Saab.
        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withController(CarController::onTurboOn);
            }
        });

        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withController(CarController::onTurboOff);
            }
        });

        // Flak-knappar gäller endast Scania.
        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withController(CarController::onLowerBeds);
            }
        });

        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withController(CarController::onRaiseBeds);
            }
        });

        // Start/stop gäller alla fordon i simuleringen.
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withController(CarController::onStartAll);
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withController(CarController::onStopAll);
            }
        });

        addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withController(CarController::onAddCar);
            }
        });

        removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withController(CarController::onRemoveCar);
            }
        });


        // Anpassa fönstret efter komponenternas preferred size.
        this.pack();

        // Hämta skärmupplösning
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Centrera fönstret
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Visa fönstret
        this.setVisible(true);
        // Avsluta programmet när fönstret stängs
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void render(List<Vehicle> vehicles) {
        // View ritar bara modelns data, ingen domänlogik här.
        drawPanel.setVehicles(vehicles);
        drawPanel.repaint();
    }

    @Override
    public void onSimulationUpdated(List<Vehicle> vehicles) {
        render(vehicles);
    }
}
