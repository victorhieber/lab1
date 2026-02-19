import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage[] carImages = new BufferedImage[3];
   Point[] carpoints = {
    new Point(0, 0),
    new Point(0, 100),
    new Point(0, 200),
};



    // To keep track of a single car's position

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    // TODO: Make this general for all cars
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

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            carImages[0] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            carImages[1] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            carImages[2] = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < carImages.length; i++) {
            g.drawImage(carImages[i], carpoints[i].x, carpoints[i].y, null);
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
