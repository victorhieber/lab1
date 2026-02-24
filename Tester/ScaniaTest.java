package Tester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScaniaTest {

    @Test
    void scaniaRampStartsAtZero() {
        Scania scania = new Scania();
        Assertions.assertEquals(0.0, scania.getRampAngle(), 0.0001);
    }

    @Test
    void scaniaCannotRaiseRampWhileMoving() {
        Scania scania = new Scania();
        scania.startEngine();
        scania.gas(1.0);

        scania.raiseramp();
        Assertions.assertEquals(0.0, scania.getRampAngle(), 0.0001);
    }

    @Test
    void scaniaCannotGasWhenRampIsUp() {
        Scania scania = new Scania();
        scania.stopEngine();
        scania.raiseramp();

        scania.startEngine();
        double before = scania.getCurrentSpeed();
        scania.gas(1.0);

        Assertions.assertEquals(before, scania.getCurrentSpeed(), 0.0001);
    }
}
