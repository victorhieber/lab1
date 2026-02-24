package lab1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// Panelen ansvarar för att rita fordonens aktuella positioner.

public class DrawPanel extends JPanel{

    // Bilder för Volvo, Saab och Scania.
    BufferedImage[] carImages = new BufferedImage[3];
    // Startpositioner i Y-led med 100 px avstånd.
    Point[] carpoints = {
        new Point(0, 0),
        new Point(0, 100),
        new Point(0, 200),
    };

    // Verkstadsbild + position i panelen.
    // Pointen är ÖVRE VÄNSTRA hörnet för bilden.
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,100);

    // Uppdaterar position för bilen med visst index.
    void moveit(int index, int x, int y){
        carpoints[index].x = x;
        carpoints[index].y = y;
    }

    int getCarWidth(int index){
        return carImages[index].getWidth();
    }
    int getCarHeight(int index){
        return carImages[index].getHeight();
    }

    // Initialiserar panelen och laddar in bilbilderna från pics/.
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Skriv fel i konsolen om bildfil saknas.
        try {
            carImages[0] = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg"));
            carImages[1] = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Saab95.jpg"));
            carImages[2] = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg"));

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // Bildens bredd/höjd = storlek på verkstaden (inte position).
    int getVolvoWorkshopWidth(){
        return volvoWorkshopImage.getWidth();
    }
    int getVolvoWorkshopHeight(){
        return volvoWorkshopImage.getHeight();
    }

    // Dessa två används när controllern behöver verkstadens position.
    int getWorkshopX(){
        return volvoWorkshopPoint.x;
    }
    int getWorkshopY(){
        return volvoWorkshopPoint.y;
    }



    // Ritas om vid varje repaint() från controllern.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < carImages.length; i++) {
            g.drawImage(carImages[i], carpoints[i].x, carpoints[i].y, null);
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
