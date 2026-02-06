import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest {

    @Test
    void scaniaRampStartsAtZero() {
        Scania scania = new Scania();
        assertEquals(0.0, scania.getRampAngle(), 0.0001);
    }

    @Test
    void scaniaCannotRaiseRampWhileMoving() {
        Scania scania = new Scania();
        scania.startEngine();
        scania.gas(1.0);

        scania.raiseramp();
        assertEquals(0.0, scania.getRampAngle(), 0.0001);
    }

    @Test
    void scaniaCannotGasWhenRampIsUp() {
        Scania scania = new Scania();
        scania.stopEngine();
        scania.raiseramp();

        scania.startEngine();
        double before = scania.getCurrentSpeed();
        scania.gas(1.0);

        assertEquals(before, scania.getCurrentSpeed(), 0.0001);
    }
}
