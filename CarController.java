import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* Den här klassen är Controller-delen i MVC.
* Den lyssnar på händelser från vyn, uppdaterar modellen och skickar
* tillbaka nya positioner till vyn.
 */

public class CarController  {

    private final VolvoWorkshop volvoWorkshop = new VolvoWorkshop(10);

    private final int delay = 50;
    // Timern triggar TimerListener vid varje tick.
    private Timer timer = new Timer(delay, new TimerListener());

    CarView frame;
    // Alla fordon i simuleringen.
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Skapa controller + de tre fordonen från labben.
        CarController cc = new CarController();
        Vehicle volvo = new Volvo240();
        Vehicle saab = new Saab95();
        Vehicle scania = new Scania();

        // Alla startar åt höger och placeras med 100 px avstånd i Y-led.
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

    /*Varje tick:
    1 flytta alla fordon i modellen
    2 hantera väggkollision (stanna, vänd, fortsätt)
    3 skicka uppdaterad position till DrawPanel
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

                // Träffar bilen en kant så klampar vi positionen till panelen
                // och inverterar riktningen (180 grader).
                boolean hitWall = x < 0 || y < 0 || x > panelW - carW || y > panelH - carH;

                if (hitWall) {
                    double clampedX = Math.max(0, Math.min(x, maxX));
                    double clampedY = Math.max(0, Math.min(y, maxY));
                    car.setPosition(clampedX, clampedY);
                    car.turnLeft();
                    car.turnLeft();
                }

                // Kollision med verkstaden
                double dx = car.getx() - frame.drawPanel.getWorkshopX();
               double dy = car.gety() - frame.drawPanel.getWorkshopY();
               double distance = Math.sqrt(dx * dx + dy * dy);
               if (distance < 10 && car instanceof Volvo240) {
                volvoWorkshop.recieveCar((Volvo240) car);
                car.stopEngine();
                car.setPosition(300,100);
               }




                // Synka modellens position till vyn.
                frame.drawPanel.moveit(i, (int) Math.round(car.getx()), (int) Math.round(car.gety()));

            }

            frame.drawPanel.repaint();
        }
    }

    // Använder spinner-värdet som gas för alla bilar.
    void gas(int amount) {
        double gas = ((double) amount) / 100;
       for (Vehicle car : cars) {
            car.gas(gas);
        }
    }

    // Samma spinner-värde används även som broms.
    void brake(int amount){
        double brake = ((double) amount) / 100;
        for (Vehicle car : cars) {
            car.brake(brake);
        }
    }

    // Turbo påverkar endast Saab.
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

    // Flaket påverkar endast Scania.
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

    // Start/stop gäller alla fordon i listan.
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
