package lab1;

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

    // Volvo-verkstad som kan lagra upp till 10 Volvo-bilar.
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

                // "Krock" med verkstaden:
                // Vi räknar avstånd från bilen till verkstadens position (x,y).
                // OBS: getWorkshopX/Y är POSITION.
                // getVolvoWorkshopWidth/Height är bara STORLEK på bilden.
                double dx = car.getx() - frame.drawPanel.getWorkshopX();
               double dy = car.gety() - frame.drawPanel.getWorkshopY();
               double distance = Math.sqrt(dx * dx + dy * dy);
               // Endast Volvo ska lastas in i Volvo-verkstaden.
               // Andra bilar ignoreras av denna if-sats
            }
        }
    }
}
