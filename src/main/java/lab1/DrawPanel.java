package lab1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

// Panelen ansvarar för att rita fordonens aktuella positioner.

public class DrawPanel extends JPanel{

    // Bilder för Volvo, Saab och Scania.
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    private List<Vehicle> vehiclesToDraw = List.of();

    // Verkstadsbild + position i panelen.
    // Pointen är ÖVRE VÄNSTRA hörnet för bilden.
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,100);

    // Ger panelen aktuell model-lista att rita.
    void setVehicles(List<Vehicle> vehicles){
        this.vehiclesToDraw = new ArrayList<>(vehicles);
    }

    // Initialiserar panelen och laddar in bilbilderna från pics/.
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        volvoImage = loadImage("pics/Volvo240.jpg");
        saabImage = loadImage("pics/Saab95.jpg");
        scaniaImage = loadImage("pics/Scania.jpg");
        volvoWorkshopImage = loadImage("pics/VolvoBrand.jpg");
    }

    //Ai hjälpte oss att fixa bilderna
    private BufferedImage loadImage(String relativePath) {
        String classpathPath = relativePath.startsWith("/") ? relativePath : "/" + relativePath;

        try (InputStream stream = DrawPanel.class.getResourceAsStream(classpathPath)) {
            if (stream != null) {
                return ImageIO.read(stream);
            }
        } catch (IOException ignored) {
            // Prova filsystemets fallback-sökvägar nedan.
        }

        Path[] fallbackPaths = new Path[] {
                Paths.get("src", "main", "resources", relativePath),
                Paths.get(relativePath),
                Paths.get("..", "src", "main", "resources", relativePath)
        };

        for (Path path : fallbackPaths) {
            try {
                if (Files.exists(path)) {
                    return ImageIO.read(path.toFile());
                }
            } catch (IOException ignored) {
                // Testa nästa sökväg.
            }
        }

        throw new IllegalStateException("Could not load image: " + relativePath);
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
        for (Vehicle vehicle : vehiclesToDraw) {
            BufferedImage image = imageForVehicle(vehicle);
            g.drawImage(image, (int) Math.round(vehicle.getx()), (int) Math.round(vehicle.gety()), null);
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }

    private BufferedImage imageForVehicle(Vehicle vehicle) {
        if (vehicle instanceof Volvo240) return volvoImage;
        if (vehicle instanceof Saab95) return saabImage;
        if (vehicle instanceof Scania) return scaniaImage;
        return volvoImage;
    }
}
