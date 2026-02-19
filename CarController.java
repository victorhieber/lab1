import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController  {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        Vehicle volvo = new Volvo240();
    Vehicle saab = new Saab95();
    Vehicle scania = new Scania();
    volvo.turnRight();
    saab.turnRight();
    scania.turnRight();
    volvo.setPosition(0, 100);
    saab.setPosition(0, 200);
    scania.setPosition(0, 300);
    cc.cars.add(volvo);
    cc.cars.add(saab);
    cc.cars.add(scania);
    cc.frame = new CarView("CarSim 1.0", cc);
    cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            

            for (int i = 0; i < cars.size(); i++) {
                Vehicle car = cars.get(i);
                car.move();

                double x = car.getx();
                double y = car.gety();

                int panelW = frame.drawPanel.getWidth();
                int panelH = frame.drawPanel.getHeight();
                int carW = frame.drawPanel.getCarWidth(i);
                int carH = frame.drawPanel.getCarHeight(i);

                double maxX = panelW - carW;
                double maxY = panelH - carH;

                boolean hitWall = x < 0 || y < 0 || x > panelW - carW || y > panelH - carH;

                if (hitWall) {
                    double clampedX = Math.max(0, Math.min(x, maxX));
                    double clampedY = Math.max(0, Math.min(y, maxY));
                    car.setPosition(clampedX, clampedY);
                    car.turnLeft();
                    car.turnLeft();
                }

                
                // repaint() calls the paintComponent method of the panel
                    frame.drawPanel.moveit(i, (int) Math.round(car.getx()), (int) Math.round(car.gety()));

            }
            
            frame.drawPanel.repaint();
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
       for (Vehicle car : cars
                ) {
            car.gas(gas);
        }
    }

    void brake(int amount){
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars
            ) {
            car.brake(brake);
        }
    }

    // CarController
    void turboOnAllSaabs() {
        for (Vehicle v : cars) {
            if (v instanceof Saab95 saab) {
                saab.setTurboOn();
            }
        }
    }

    void turboOffAllSaabs() {
        for (Vehicle v : cars) {
            if (v instanceof Saab95 saab) {
                saab.setTurboOff();
            }
        }
    }

    void lowerAllScaniaBeds() {
        for (Vehicle v : cars) {
            if (v instanceof Scania scania) {
                scania.lowerramp();
            }
        }
    }

    void liftAllScaniaBeds() {
        for (Vehicle v : cars) {
            if (v instanceof Scania scania) {
                scania.raiseramp();
            }
        }
    }

    void startAllCars() {
        for (Vehicle v : cars) {
            v.startEngine();
        }
    }

    void stopAllCars() {
        for (Vehicle v : cars) {
            v.stopEngine();
        }
    }

}
