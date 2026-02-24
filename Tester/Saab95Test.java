package Tester;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {

    @Test
    void speedFactor() {
        Saab95 saab = new Saab95();

        // Testa med turbo av (standard)
        saab.setTurboOff();
        double factorOff = saab.speedFactor();
        assertEquals(1.25, factorOff, 0.01); // 125 * 0.01 * 1.0 = 1.25

        // Testa med turbo på
        saab.setTurboOn();
        double factorOn = saab.speedFactor();
        assertEquals(1.625, factorOn, 0.01); // 125 * 0.01 * 1.3 = 1.625
    }

    @Test
    void incrementSpeed() {
        Saab95 saab = new Saab95();
        saab.startEngine(); // Fart börjar på 0.1

        double initialSpeed = saab.getCurrentSpeed();
        saab.gas(0.5);

        // Vi kollar att farten faktiskt har blivit högre än startfarten
        assertTrue(saab.getCurrentSpeed() > initialSpeed,
                "Farten borde öka när man gasar");
    }

    @Test
    void decrementSpeed() {
        Saab95 saab = new Saab95();
        saab.startEngine();
        saab.gas(1.0); // Öka farten lite

        double speedBeforeBrake = saab.getCurrentSpeed();
        saab.brake(0.5);

        // Vi kollar att farten har minskat
        assertTrue(saab.getCurrentSpeed() < speedBeforeBrake,
                "Farten borde minska när man bromsar");
    }
}